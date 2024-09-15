package com.Aaron.MFM.web.app.service.impl;


import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.common.rabbitmq.RabbitConfig;
import com.Aaron.MFM.common.websocket.WebSocketServer;
import com.Aaron.MFM.model.entity.OrderFoodRelation;
import com.Aaron.MFM.model.entity.OrderInfo;
import com.Aaron.MFM.model.entity.SalesPromotion;
import com.Aaron.MFM.web.app.mapper.OrderFoodRelationMapper;
import com.Aaron.MFM.web.app.mapper.OrderInfoMapper;
import com.Aaron.MFM.web.app.mapper.SalesPromotionMapper;
import com.Aaron.MFM.web.app.service.ISalesPromotionService;
import com.Aaron.MFM.web.app.vo.order.OrderInfoVo;
import com.Aaron.MFM.web.app.vo.order.OrderVo;
import com.Aaron.MFM.web.app.vo.order.SalesPromotionVo;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 促销表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class SalesPromotionServiceImpl extends ServiceImpl<SalesPromotionMapper, SalesPromotion> implements ISalesPromotionService {

    @Autowired
    private SalesPromotionMapper salesPromotionMapper;

    @Autowired
    @Qualifier("redisSToITemplate")
    private RedisTemplate<String,Integer> redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderFoodRelationMapper orderFoodRelationMapper;

    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    public List<SalesPromotionVo> getSalePromotionList() {

        return salesPromotionMapper.getSalePromotionList();
    }

    @Override
    public void snapped(Integer id) {
        // 获取用户信息
        Long userId = LoginHolder.getLoginUser().getId();
        // TODO
        webSocketServer.sendMessage(userId.toString(), "抢购成功");
        String LOCAK_KEY = "salesPromotion";
        // 获取锁
        boolean lockAcquired = redisTemplate.opsForValue().setIfAbsent(LOCAK_KEY,1, Duration.ofSeconds(1));
        if(lockAcquired){
            try{
                // 查询库存是否充足
                Integer number = redisTemplate.opsForValue().get("salesPromotion:" + id);
                if(number == null || number <= 0){
                    throw new MFMException(201,"库存不足");
                }
                // 库存减一
                redisTemplate.opsForValue().decrement("salesPromotion:" + id);
            }finally {
                // 释放锁
                redisTemplate.delete(LOCAK_KEY);
            }
        }else {
            throw new MFMException(201,"系统繁忙,请重试");
        }
        // 把订单信息存入消息队列
        String order = userId+":"+id;

        rabbitTemplate.convertAndSend(RabbitConfig.ORDER_EXCHANGE,RabbitConfig.ORDER_ROUTING_KEY,order);
    }
    @RabbitListener(queues = RabbitConfig.ORDER_QUEUE)
    public void orderListener(String order){
        String[] split = order.split(":");
        Long userId = Long.parseLong(split[0]);
        Integer salesPromotionId = Integer.parseInt(split[1]);
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

}
