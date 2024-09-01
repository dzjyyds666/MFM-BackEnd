package com.Aaron.MFM.web.app.service.impl;

import com.Aaron.MFM.model.entity.OrderStatus;
import com.Aaron.MFM.web.app.mapper.OrderStatusMapper;
import com.Aaron.MFM.web.admin.service.IOrderStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
