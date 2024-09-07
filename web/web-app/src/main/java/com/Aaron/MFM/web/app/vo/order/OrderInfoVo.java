package com.Aaron.MFM.web.app.vo.order;

import com.Aaron.MFM.model.entity.FoodInfo;
import com.Aaron.MFM.model.entity.OrderInfo;
import com.Aaron.MFM.model.entity.OrderStatus;
import com.Aaron.MFM.web.app.vo.food.FoodInfoVo;
import lombok.Data;

import java.util.List;

@Data
public class OrderInfoVo extends OrderInfo {

    private List<FoodInfo> foodInfoList;

    private OrderStatus orderStatus;

}
