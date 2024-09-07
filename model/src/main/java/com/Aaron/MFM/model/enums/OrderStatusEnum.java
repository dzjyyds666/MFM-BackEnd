package com.Aaron.MFM.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    PALCE_ORDER(1, "已下单"),
    PAID(2, "已支付"),
    CANCELDEL(3, "已取消"),
    FINISHED(4, "已出餐");


    @EnumValue
    private Integer value;
    private String desc;


    OrderStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }


}
