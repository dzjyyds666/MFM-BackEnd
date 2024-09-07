package com.Aaron.MFM.web.app.service;

import com.Aaron.MFM.model.entity.OrderStatus;
import com.Aaron.MFM.web.app.vo.order.OrderInfoVo;
import com.Aaron.MFM.web.app.vo.order.OrderStatusVo;
import com.Aaron.MFM.web.app.vo.order.OrderVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单状态表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
public interface IOrderStatusService extends IService<OrderStatus> {

    void addOrder(OrderVo orderInfo);

    void updateOrder(OrderStatusVo orderStatusVo);

    List<OrderInfoVo> getOrderById(Long userId, Integer id);
}
