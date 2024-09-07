package com.Aaron.MFM.web.app.service.impl;


import com.Aaron.MFM.model.entity.OrderInfo;
import com.Aaron.MFM.web.admin.service.IOrderInfoService;
import com.Aaron.MFM.web.app.mapper.OrderInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
