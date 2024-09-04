package com.Aaron.MFM.web.admin.service;

import com.Aaron.MFM.model.entity.OrderInfo;
import com.Aaron.MFM.web.admin.vo.order.OrderInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    List<OrderInfoVo> getOrderList();

    OrderInfoVo getOrderById(Integer id);

    void upOrderStatus(Integer id, Integer statusId);

    List<OrderInfoVo> searchByDate(String date);
}
