package com.Aaron.MFM.web.app.vo.pay;

import lombok.Data;

@Data
public class AliPayVo {

    private String orderNumber;

    // 订单金额
    private Integer totalAmount;
}
