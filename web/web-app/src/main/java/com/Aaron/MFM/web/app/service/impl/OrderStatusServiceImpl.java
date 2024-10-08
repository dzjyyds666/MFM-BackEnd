package com.Aaron.MFM.web.app.service.impl;


import com.Aaron.MFM.common.Login.LoginHolder;
import com.Aaron.MFM.common.rabbitmq.RabbitConfig;
import com.Aaron.MFM.model.entity.OrderFoodRelation;
import com.Aaron.MFM.model.entity.OrderInfo;
import com.Aaron.MFM.model.entity.OrderStatus;
import com.Aaron.MFM.model.entity.UserInfo;
import com.Aaron.MFM.web.app.mapper.OrderFoodRelationMapper;
import com.Aaron.MFM.web.app.mapper.OrderInfoMapper;
import com.Aaron.MFM.web.app.mapper.OrderStatusMapper;
import com.Aaron.MFM.web.app.service.IOrderStatusService;
import com.Aaron.MFM.web.app.vo.order.OrderInfoVo;
import com.Aaron.MFM.web.app.vo.order.OrderStatusVo;
import com.Aaron.MFM.web.app.vo.order.OrderVo;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 订单状态表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class OrderStatusServiceImpl extends ServiceImpl<OrderStatusMapper, OrderStatus> implements IOrderStatusService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderFoodRelationMapper orderFoodRelationMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public String addOrder(OrderVo orderVo) {
        UserInfo loginUser = LoginHolder.getLoginUser();
        OrderInfo orderInfo = new OrderInfo();
        String orderNumber = UUID.randomUUID().toString().replaceAll("-","A").substring(0,16);
        orderInfo.setOrderNumber(orderNumber);
        orderInfo.setTotal(orderVo.getTotal());
        orderInfo.setStausId(1);
        orderInfo.setUserId(loginUser.getId());

        orderInfoMapper.insert(orderInfo);

        HashMap<Integer,Integer> map = new HashMap<>();
        for (Integer i : orderVo.getFoodIdList()) {
            if(map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }else{
                map.put(i,1);
            }
        }

        map.forEach((key,value)->{
            OrderFoodRelation orderFoodRelation = new OrderFoodRelation();
            orderFoodRelation.setOrderId(orderInfo.getId());
            orderFoodRelation.setFoodId(key);
            orderFoodRelation.setNumber(value);
            orderFoodRelationMapper.insert(orderFoodRelation);
        });

        MessagePostProcessor postProcessor = message -> {
            // 设置过期时间  15分钟订单自动过期
            message.getMessageProperties().setHeader("x-delay", 1000 * 60 * 15);
            return message;
        };

        // 发送订单信息到rabbitmq, 15分钟未支付自动取消
        rabbitTemplate.convertAndSend(RabbitConfig.DELAYED_EXCHANGE_NAME,// 交换机
                RabbitConfig.DELAYED_ROUTING_KEY,// 路由键
                orderInfo.getOrderNumber(),// 消息内容
                postProcessor
               );

        return orderNumber;
    }

    @Override
    public void updateOrder(OrderStatusVo orderStatusVo) {
        LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(OrderInfo::getOrderNumber,orderStatusVo.getOrderNumber());
        updateWrapper.set(OrderInfo::getStausId,orderStatusVo.getStausId());
        orderInfoMapper.update(null,updateWrapper);
    }

    @Override
    public List<OrderInfoVo> getOrderById(Long userId, Integer id) {
        return orderInfoMapper.getOrderById(userId,id);
    }

}
