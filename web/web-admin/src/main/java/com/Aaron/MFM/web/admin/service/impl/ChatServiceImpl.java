package com.Aaron.MFM.web.admin.service.impl;

import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.chat.ChatVo;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.rabbitmq.RabbitConfig;
import com.Aaron.MFM.model.entity.ChatInfo;

import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.admin.custom.websocket.ChatSocketServer;
import com.Aaron.MFM.web.admin.mapper.ChatInfoMapper;
import com.Aaron.MFM.web.admin.mapper.UserInfoMapper;
import com.Aaron.MFM.web.admin.service.IChatService;
import com.Aaron.MFM.web.admin.vo.chat.ChatNotReadVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class ChatServiceImpl extends ServiceImpl<ChatInfoMapper, ChatInfo> implements IChatService {

    @Autowired
    private ChatInfoMapper chatInfoMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();
    // 获取聊天记录
    @Override
    public List<ChatInfo> getChatList(Long receiverId) {
        Long senderId = LoginHolder.getLoginUser().getId();
        LambdaQueryWrapper<ChatInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatInfo::getSenderId, senderId);
        queryWrapper.eq(ChatInfo::getReceiverId, receiverId);
        queryWrapper.orderByAsc(ChatInfo::getCreateTime);
        List<ChatInfo> chatInfo1 = chatInfoMapper.selectList(queryWrapper);

        LambdaQueryWrapper<ChatInfo> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(ChatInfo::getSenderId, receiverId);
        queryWrapper2.eq(ChatInfo::getReceiverId, senderId);
        queryWrapper2.orderByAsc(ChatInfo::getCreateTime);
        List<ChatInfo> chatInfo2 = chatInfoMapper.selectList(queryWrapper2);

        // 合并两个list, 按照时间升序排序
        chatInfo1.addAll(chatInfo2);
        chatInfo1.sort(Comparator.comparing(ChatInfo::getCreateTime));

        // 设置状态为已读
        for (ChatInfo chatInfo : chatInfo1) {
            if(chatInfo.getIsRead() == 0 && chatInfo.getReceiverId().equals(senderId)){
                LambdaUpdateWrapper<ChatInfo> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(ChatInfo::getId,chatInfo.getId()).eq(ChatInfo::getReceiverId, senderId).set(ChatInfo::getIsRead,1);
                chatInfoMapper.update(null ,updateWrapper);
                chatInfo.setIsRead((byte)1);
            }
        }
        return chatInfo1;
    }

    @Override
    public String sendMessage(ChatVo chatVo) {
        ChatInfo chatinfo = new ChatInfo();
        chatinfo.setId(String.valueOf(UUID.randomUUID()).substring(0,10).replace("-","A"));
        chatinfo.setSenderId(chatVo.getSenderId());
        chatinfo.setReceiverId(chatVo.getReceiverId());
        chatinfo.setContent(chatVo.getContent());
        chatinfo.setType(chatVo.getType());
        String message = null;
        try{
            message = objectMapper.writeValueAsString(chatVo);
        }catch(Exception e){
            throw new MFMException(201,"序列化失败");
        }
        rabbitTemplate.convertAndSend(RabbitConfig.CHAT_EXCHANGE_TO_USER , RabbitConfig.CHAT_ROUTING_KEY_TO_USER, message);
        return "发送成功";
    }

    @Override
    public List<ChatNotReadVo> getChatUserAndNotRead() {
        // 获取当前登录用户的id
        Long senderId = LoginHolder.getLoginUser().getId();
        // 查询所有发送给其他用户的消息
        LambdaQueryWrapper<ChatInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatInfo::getSenderId, senderId);
        // 查询所有接收其他用户的消息
        LambdaQueryWrapper<ChatInfo> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(ChatInfo::getReceiverId, senderId);
        List<ChatInfo> chatInfos = chatInfoMapper.selectList(queryWrapper);
        List<ChatInfo> chatInfos1 = chatInfoMapper.selectList(queryWrapper1);
        chatInfos.addAll(chatInfos1);
        // 去重,获取所有发送或接收到的用户id
        Set<Long> set = new HashSet<>();
        for (ChatInfo chatInfo : chatInfos) {
            if(chatInfo.getSenderId().equals(senderId)){
                set.add(chatInfo.getReceiverId());
            }else if(chatInfo.getReceiverId().equals(senderId)) {
                set.add(chatInfo.getSenderId());
            }
        }
        // 查询所有用户信息
        LambdaQueryWrapper<UserInfo> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.in(UserInfo::getId,set);
        List<UserInfo> userInfos = userInfoMapper.selectList(queryWrapper2);
        // 封装数据
        List<ChatNotReadVo> list = new ArrayList<>();
        for (UserInfo userInfo : userInfos) {
            // 查询未读消息数量
            LambdaQueryWrapper<ChatInfo> queryWrapper3 = new LambdaQueryWrapper<>();
            queryWrapper3.eq(ChatInfo::getSenderId,userInfo.getId()).eq(ChatInfo::getIsRead,0);
            int size = Math.toIntExact(chatInfoMapper.selectCount(queryWrapper3));

            // 封装数据
            ChatNotReadVo chatNotReadVo = new ChatNotReadVo();
            chatNotReadVo.setId(userInfo.getId());
            chatNotReadVo.setAvatarUrl(userInfo.getAvatarUrl());
            chatNotReadVo.setNickName(userInfo.getNickname());
            chatNotReadVo.setNotReadNumber(size);
            list.add(chatNotReadVo);
        }
        // 排序, 按照未读消息数量降序排列
        list.sort(Comparator.comparingInt(ChatNotReadVo::getNotReadNumber).reversed());
        return list;
    }

    @Override
    public List<ChatInfo> getMessageOneday(Long receiverId) {
        Long senderId = LoginHolder.getLoginUser().getId();
        String redis_key = "chat_info_in_oneday:"+senderId+"-"+receiverId;
        String json = redisTemplate.opsForValue().get(redis_key);
        // 注册JavaTimeModule来处理LocalDateTime
        objectMapper.registerModule(new JavaTimeModule());
        List<ChatInfo> chatInfoList = new ArrayList<>();
        HashMap<String,Object> hashMap;
        try{
            hashMap = objectMapper.readValue(json, HashMap.class);
            if(hashMap == null || hashMap.isEmpty()){
                return null;
            }else {
                hashMap.forEach((key,value)->{
                    LinkedHashMap<String,Object> map = (LinkedHashMap<String, Object>) value;
                    ChatInfo chatinfo = objectMapper.convertValue(map,ChatInfo.class);
                    chatInfoList.add(chatinfo);
                    System.out.println(chatinfo);
                });
            }
        }catch (Exception e){
            throw new MFMException(201,"反序列化失败");
        }

        // 删除时间大于24小时的消息
        int hasExpired = 0;
        //chatInfoList.removeIf(chatInfo -> chatInfo.getCreateTime().isBefore(LocalDateTime.now().minusDays(1)));
        Iterator<ChatInfo> iterator = chatInfoList.iterator();
        while (iterator.hasNext()) {
            ChatInfo chatInfo = iterator.next();
            if(chatInfo.getCreateTime().isBefore(LocalDateTime.now().minusDays(1))){
                iterator.remove();
                hasExpired++;
            }
        }

        chatInfoList.sort(Comparator.comparing(ChatInfo::getCreateTime));
        // 如果有数据过期,更新redis中的数据
        if(hasExpired > 0) {
            HashMap<String, Object> map = new HashMap<>();
            chatInfoList.forEach(chatInfo -> map.put(chatInfo.getCreateTime().toString(), chatInfo));
            try {
                String json1 = objectMapper.writeValueAsString(map);
                redisTemplate.opsForValue().set(redis_key, json1);
            } catch (Exception e) {
                throw new MFMException(201, "序列化失败");
            }
        }

        // 将未读消息设置为已读
        for (ChatInfo chatInfo : chatInfoList) {
            if (chatInfo.getIsRead() == 0 && chatInfo.getReceiverId().equals(senderId)) {
                LambdaUpdateWrapper<ChatInfo> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(ChatInfo::getId,chatInfo.getId()).eq(ChatInfo::getReceiverId,senderId).set(ChatInfo::getIsRead,1);
                chatInfoMapper.update(null,updateWrapper);
                chatInfo.setIsRead((byte)1);
            }
        }
        return chatInfoList;
    }

}
