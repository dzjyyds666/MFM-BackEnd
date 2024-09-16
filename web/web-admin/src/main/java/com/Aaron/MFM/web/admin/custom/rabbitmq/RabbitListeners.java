package com.Aaron.MFM.web.admin.custom.rabbitmq;


import com.Aaron.MFM.common.chat.ChatVo;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.rabbitmq.RabbitConfig;
import com.Aaron.MFM.model.entity.ChatInfo;
import com.Aaron.MFM.model.entity.OrderInfo;
import com.Aaron.MFM.model.enums.OrderStatusEnum;
import com.Aaron.MFM.web.admin.custom.websocket.ChatSocketServer;
import com.Aaron.MFM.web.admin.mapper.ChatInfoMapper;
import com.Aaron.MFM.web.admin.mapper.OrderInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
@Slf4j
public class RabbitListeners {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private ChatInfoMapper chatInfoMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ChatSocketServer chatSocketServer;
    // 监听订单过期
    @RabbitListener(queues = RabbitConfig.DELAYED_QUEUE_NAME)
    public void orderExpire(String orderNumber) {
        // 查询订单信息
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderInfo::getOrderNumber, orderNumber);
        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);

        // 判断订单状态, 只有未支付订单才修改状态为已取消
        if(orderInfo.getStausId() == OrderStatusEnum.PALCE_ORDER.getValue()){
            LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(OrderInfo::getOrderNumber, orderNumber);
            updateWrapper.set(OrderInfo::getStausId, OrderStatusEnum.CANCELDEL.getValue());
            orderInfoMapper.update(null, updateWrapper);
        }
    }


    @RabbitListener(queues = RabbitConfig.CHAT_QUEUE_TO_SELLER)
    public void chatFormUser(String msg){
        // 将消息转换为ChatVo
        ChatVo chatVo = null;
        try {
            chatVo = objectMapper.readValue(msg, ChatVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        chatSocketServer.sendMessage(chatVo.getReceiverId().toString(), msg);
        String id = String.valueOf(UUID.randomUUID()).substring(0,10).replace("-","A");
        // 将消息写入数据库
        if (chatVo != null){
            ChatInfo chatinfo = new ChatInfo();
            chatinfo.setId(id);
            chatinfo.setSenderId(chatVo.getSenderId());
            chatinfo.setReceiverId(chatVo.getReceiverId());
            chatinfo.setContent(chatVo.getContent());
            chatinfo.setType(chatVo.getType());
            chatInfoMapper.insert(chatinfo);
        }

        ChatInfo chatinfo = chatInfoMapper.selectById(id);

        // 缓存消息,一条消息缓存一天
        String redis_key = "chat_info_in_oneday:"+chatVo.getReceiverId()+"-"+chatVo.getSenderId();
        String message = redisTemplate.opsForValue().get(redis_key);
        // 注册JavaTimeModule来处理LocalDateTime
        objectMapper.registerModule(new JavaTimeModule());
        if(message ==  null){
            try{
                HashMap<String,Object> map = new HashMap<>();
                map.put(chatinfo.getCreateTime().toString(),chatinfo);
                String json = objectMapper.writeValueAsString(map);
                redisTemplate.opsForValue().set(redis_key,json);
            }catch (Exception e){
                throw new MFMException(201,"序列化失败");
            }
        }else {
            try{
                HashMap hashMap = objectMapper.readValue(message, HashMap.class);
                hashMap.put(chatinfo.getCreateTime().toString(),chatinfo);
                String json = objectMapper.writeValueAsString(hashMap);
                redisTemplate.opsForValue().set(redis_key,json);
            }catch (Exception e){
                throw new MFMException(201,"反序列化失败");
            }
        }
    }
}
