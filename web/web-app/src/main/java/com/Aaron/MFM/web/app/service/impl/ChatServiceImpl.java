package com.Aaron.MFM.web.app.service.impl;

import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.chat.ChatVo;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.rabbitmq.RabbitConfig;
import com.Aaron.MFM.model.entity.ChatInfo;
import com.Aaron.MFM.web.app.mapper.ChatInfoMapper;
import com.Aaron.MFM.web.app.service.IChatService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ChatServiceImpl extends ServiceImpl<ChatInfoMapper, ChatInfo> implements IChatService {


    @Autowired
    private ChatInfoMapper chatInfoMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Long ReceiverId = 1L;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Integer getNotReadNumber() {
        Long senderId = LoginHolder.getLoginUser().getId();
        LambdaQueryWrapper<ChatInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatInfo::getReceiverId, senderId);
        queryWrapper.eq(ChatInfo::getIsRead,0);
        Long number = chatInfoMapper.selectCount(queryWrapper);
        return number.intValue();
    }

    @Override
    public List<ChatInfo> getMessageOneday() {
        Long senderId = LoginHolder.getLoginUser().getId();
        String redis_key = "chat_info_in_oneday:"+ReceiverId+"-"+senderId;
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
        // 设置状态为已读
        for(ChatInfo chatInfo:chatInfoList){
            if (chatInfo.getIsRead() == 0 && chatInfo.getReceiverId().equals(senderId)){
                LambdaUpdateWrapper<ChatInfo> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(ChatInfo::getId,chatInfo.getId()).eq(ChatInfo::getReceiverId,senderId).set(ChatInfo::getIsRead,1);
                chatInfoMapper.update(null ,updateWrapper);
                chatInfo.setIsRead((byte)1);
            }
        }
        return chatInfoList;
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
        rabbitTemplate.convertAndSend(RabbitConfig.CHAT_EXCHANGE_TO_SELLER , RabbitConfig.CHAT_ROUTING_KEY_TO_SELLER, message);
        return "发送成功";
    }

    @Override
    public List<ChatInfo> getMessageList() {
        Long senderId = LoginHolder.getLoginUser().getId();
        LambdaQueryWrapper<ChatInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatInfo::getSenderId,senderId);
        queryWrapper.eq(ChatInfo::getReceiverId,ReceiverId);
        List<ChatInfo> chatInfos = chatInfoMapper.selectList(queryWrapper);
        LambdaQueryWrapper<ChatInfo> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(ChatInfo::getSenderId,ReceiverId);
        queryWrapper2.eq(ChatInfo::getReceiverId,senderId);
        List<ChatInfo> chatInfos2 = chatInfoMapper.selectList(queryWrapper2);
        chatInfos.addAll(chatInfos2);
        chatInfos.sort(Comparator.comparing(ChatInfo::getCreateTime));
        for (ChatInfo chatInfo : chatInfos){
            if(chatInfo.getIsRead() == 0 && chatInfo.getReceiverId().equals(senderId)){
                LambdaUpdateWrapper<ChatInfo> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(ChatInfo::getId,chatInfo.getId()).eq(ChatInfo::getReceiverId,senderId).set(ChatInfo::getIsRead,1);
                chatInfoMapper.update(null ,updateWrapper);
                chatInfo.setIsRead((byte)1);
            }
        }
        return chatInfos;
    }
}
