package com.Aaron.MFM.web.admin.service.impl;

import com.Aaron.MFM.common.rabbitmq.RabbitConfig;
import com.Aaron.MFM.model.entity.OrderFoodRelation;
import com.Aaron.MFM.model.entity.OrderInfo;
import com.Aaron.MFM.model.entity.OrderStatus;
import com.Aaron.MFM.model.enums.OrderStatusEnum;
import com.Aaron.MFM.web.admin.mapper.FoodInfoMapper;
import com.Aaron.MFM.web.admin.mapper.OrderFoodRelationMapper;
import com.Aaron.MFM.web.admin.mapper.OrderInfoMapper;
import com.Aaron.MFM.web.admin.mapper.OrderStatusMapper;
import com.Aaron.MFM.web.admin.service.IOrderInfoService;
import com.Aaron.MFM.web.admin.vo.food.FoodInfoVo;
import com.Aaron.MFM.web.admin.vo.order.OrderInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderFoodRelationMapper orderFoodRelationMapper;

    @Autowired
    private FoodInfoMapper foodInfoMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Override
    public List<OrderInfoVo> getOrderList() {
        List<OrderInfoVo> list = new ArrayList<>();

        // 先查询出订单信息
        List<OrderInfo> orderInfoList = orderInfoMapper.selectList(null);
        for (OrderInfo orderInfo : orderInfoList) {
            OrderInfoVo orderInfoVo = new OrderInfoVo();
            // 订单基础信息
            orderInfoVo.setId(orderInfo.getId());
            orderInfoVo.setOrderNumber(orderInfo.getOrderNumber());
            orderInfoVo.setTotal(orderInfo.getTotal());
            orderInfoVo.setStausId(orderInfo.getStausId());
            orderInfoVo.setCreateTime(orderInfo.getCreateTime());
            orderInfoVo.setUserId(orderInfo.getUserId());

            //查询订单和食品关联信息
            LambdaQueryWrapper<OrderFoodRelation> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(OrderFoodRelation::getOrderId, orderInfo.getId());
            List<OrderFoodRelation> orderFoodRelationList = orderFoodRelationMapper.selectList(queryWrapper);

            List<FoodInfoVo> foodList = new ArrayList<>();
            for (OrderFoodRelation orderFoodRelation : orderFoodRelationList) {
                //查询关联食品信息
                FoodInfoVo foodInfoVo = foodInfoMapper.getFoodListById(orderFoodRelation.getFoodId());
                foodList.add(foodInfoVo);
            }
            orderInfoVo.setFoodInfoVoList(foodList);

            // 查询订单状态信息
            LambdaQueryWrapper<OrderStatus> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(OrderStatus::getId, orderInfo.getStausId());
            OrderStatus orderStatus = orderStatusMapper.selectOne(queryWrapper1);

            orderInfoVo.setOrderStatus(orderStatus);

            list.add(orderInfoVo);
        }

        return list;
    }

    @Override
    public OrderInfoVo getOrderById(Integer id) {
        OrderInfoVo orderInfoVo = new OrderInfoVo();
        OrderInfo orderInfo = orderInfoMapper.selectById(id);
        orderInfoVo.setId(orderInfo.getId());
        orderInfoVo.setOrderNumber(orderInfo.getOrderNumber());
        orderInfoVo.setTotal(orderInfo.getTotal());
        orderInfoVo.setStausId(orderInfo.getStausId());
        orderInfoVo.setCreateTime(orderInfo.getCreateTime());
        orderInfoVo.setUserId(orderInfo.getUserId());

        // 获取食品信息
        List<OrderFoodRelation> orderFoodRelationList = orderFoodRelationMapper.selectList(new LambdaQueryWrapper<OrderFoodRelation>().eq(OrderFoodRelation::getOrderId, id));
        List<FoodInfoVo> foodList = new ArrayList<>();
        for (OrderFoodRelation orderFoodRelation : orderFoodRelationList) {
            FoodInfoVo foodInfoVo = foodInfoMapper.getFoodListById(orderFoodRelation.getFoodId());
            foodList.add(foodInfoVo);
        }
        orderInfoVo.setFoodInfoVoList(foodList);

        // 获取订单状态信息
        LambdaQueryWrapper<OrderStatus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderStatus::getId, orderInfo.getStausId());
        OrderStatus orderStatus = orderStatusMapper.selectOne(queryWrapper);

        orderInfoVo.setOrderStatus(orderStatus);

        return orderInfoVo;
    }

    @Override
    public void upOrderStatus(Integer id, Integer statusId) {
        LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(OrderInfo::getId, id).set(OrderInfo::getStausId, statusId);
        orderInfoMapper.update(null, updateWrapper);
    }

    @Override
    public List<OrderInfoVo> searchByDate(String date) {
        List<OrderInfoVo> orderList = getOrderList();

        List<OrderInfoVo> list = new ArrayList<>();
        for (OrderInfoVo orderInfoVo : orderList) {
            log.error(orderInfoVo.getCreateTime().toString().substring(0,10));
            String createtime = orderInfoVo.getCreateTime().toString().substring(0,10);
            if (createtime.equals(date)) {
                list.add(orderInfoVo);
            }
        }

        return list;
    }


    @RabbitListener(queues = RabbitConfig.DELAYED_QUEUE_NAME)
    public void orderExpire(String orderNumber) {
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderInfo::getOrderNumber, orderNumber);
        OrderInfo orderInfo = orderInfoMapper.selectOne(queryWrapper);

        if(orderInfo.getStausId() == OrderStatusEnum.PALCE_ORDER.getValue()){
            LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(OrderInfo::getOrderNumber, orderNumber);
            updateWrapper.set(OrderInfo::getStausId, OrderStatusEnum.CANCELDEL.getValue());
            orderInfoMapper.update(null, updateWrapper);
        }

    }
}
