package com.Aaron.MFM.web.app.mapper;

import com.Aaron.MFM.model.entity.OrderInfo;
import com.Aaron.MFM.web.app.vo.order.OrderInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Aaron
 * @since 2024-08-30
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    List<OrderInfoVo> getOrderById(Long userId, Integer id);
}
