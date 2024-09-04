package com.Aaron.MFM.web.admin.vo.order;

import com.Aaron.MFM.model.entity.*;
import com.Aaron.MFM.web.admin.vo.food.FoodInfoVo;
import lombok.Data;

import java.util.List;

@Data
public class OrderInfoVo extends OrderInfo {

    private List<FoodInfoVo> foodInfoVoList;

    private OrderStatus orderStatus;

}
