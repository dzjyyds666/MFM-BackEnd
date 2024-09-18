package com.Aaron.MFM.web.app.custom.rabbitmq;

import com.Aaron.MFM.common.chat.ChatVo;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.rabbitmq.RabbitConfig;
import com.Aaron.MFM.model.entity.ChatInfo;
import com.Aaron.MFM.model.entity.OrderFoodRelation;
import com.Aaron.MFM.model.entity.OrderInfo;
import com.Aaron.MFM.model.entity.SalesPromotion;
import com.Aaron.MFM.web.app.custom.websocket.ChatSocketServer;
import com.Aaron.MFM.web.app.mapper.ChatInfoMapper;
import com.Aaron.MFM.web.app.mapper.OrderFoodRelationMapper;
import com.Aaron.MFM.web.app.mapper.OrderInfoMapper;
import com.Aaron.MFM.web.app.mapper.SalesPromotionMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.UUID;

@Component
@Slf4j
public class RabbitListeners {

    @Autowired
    private SalesPromotionMapper salesPromotionMapper;

//    @Autowired
//    @Qualifier("HashMapTemplate")
//    private RedisTemplate<String, HashMap<String,ChatInfo>> redisTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderFoodRelationMapper orderFoodRelationMapper;

    @Autowired
    private ChatInfoMapper chatInfoMapper;

    @Autowired
    private ChatSocketServer chatSocketServer;;

    private final ObjectMapper objectMapper = new ObjectMapper();


    // 监听订单消息, 将订单信息写入数据库
    @RabbitListener(queues = RabbitConfig.ORDER_QUEUE)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void orderListener(String order){
        log.info("监听到订单消息:"+order);
        String[] split = order.split(":");
        Long userId = Long.parseLong(split[0]);
        Integer salesPromotionId = Integer.parseInt(split[1]);
        String orderNumber = split[2];
        // 获取促销信息
        SalesPromotion salesPromotion = salesPromotionMapper.selectById(salesPromotionId);

        if(salesPromotion.getNumber()<= 0){
            throw new MFMException(201,"库存不足");
        }

        // 减少库存
        LambdaUpdateWrapper<SalesPromotion> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SalesPromotion::getId,salesPromotionId).set(SalesPromotion::getNumber,salesPromotion.getNumber()-1);
        salesPromotionMapper.update(null,updateWrapper);

        // 设置订单信息
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(userId);
        orderInfo.setOrderNumber(UUID.randomUUID().toString().replaceAll("-","A").substring(0,16));
        orderInfo.setTotal(salesPromotion.getPrice());
        orderInfo.setStausId(2);
        // 插入订单信息
        orderInfoMapper.insert(orderInfo);

        // 插入订单和菜品关系
        OrderFoodRelation orderFoodRelation = new OrderFoodRelation();
        orderFoodRelation.setOrderId(orderInfo.getId());
        orderFoodRelation.setFoodId(salesPromotion.getFoodId());
        orderFoodRelationMapper.insert(orderFoodRelation);
    }

    // 监听聊天消息, 将消息写入数据库
    @RabbitListener(queues = RabbitConfig.CHAT_QUEUE_TO_USER)
    public void chatFormSeller(String msg){
        // 将消息转换为ChatVo
        ChatVo chatVo = null;
        try {
            chatVo = objectMapper.readValue(msg, ChatVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 发送消息
        chatSocketServer.sendMessage(chatVo.getReceiverId().toString(),msg);
        // 将消息写入数据库
        String id = String.valueOf(UUID.randomUUID()).substring(0,10).replace("-","A");
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

        String redis_key = "chat_info_in_oneday:"+chatVo.getSenderId()+"-"+chatVo.getReceiverId();
        // 缓存消息,一条消息缓存一天
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
