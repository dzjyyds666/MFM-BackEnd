package com.Aaron.MFM.web.app.service;

import com.Aaron.MFM.web.app.vo.pay.AliPayVo;

public interface IAliPayService {
    String payForOrder(AliPayVo aliPayVo);
}
