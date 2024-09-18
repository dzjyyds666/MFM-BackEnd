package com.Aaron.MFM.web.app.service.impl;

import com.Aaron.MFM.common.exception.MFMException;
import com.Aaron.MFM.model.entity.OrderInfo;
import com.Aaron.MFM.model.enums.OrderStatusEnum;
import com.Aaron.MFM.web.app.custom.config.AliPayConfig;
import com.Aaron.MFM.web.app.service.IAliPayService;
import com.Aaron.MFM.web.app.vo.pay.AliPayVo;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeAdvanceConsultRequest;
import com.alipay.api.response.AlipayTradeAdvanceConsultResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class AliPayServiceImpl implements IAliPayService {


    @Autowired
    private AliPayConfig aliPayConfig;

    @Autowired
    private OrderInfoServiceImpl orderService;

    @Override
    public String payForOrder(AliPayVo aliPayVo) {
        return "";
    }



    /*@Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public String payForOrder(AliPayVo aliPayVo) {
        // TODO 判断订单是否属于当前用户
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderInfo::getOrderNumber,aliPayVo.getOrderNumber());
        OrderInfo orderInfo = orderService.getOne(queryWrapper);
        if(orderInfo == null){
            return "订单不存在";
        }
        if (orderInfo.getStausId().equals(OrderStatusEnum.PAID.getValue())){
            return "请勿重复支付订单";
        }
        if (Objects.equals(orderInfo.getStausId(), OrderStatusEnum.CANCELDEL.getValue())){
            return "订单已取消";
        }

        AlipayClient alipayClient = new DefaultAlipayClient(
                aliPayConfig.getServerUrl(),
                aliPayConfig.getAppId(),
                aliPayConfig.getMerchantPrivateKey(),
                aliPayConfig.getFormat(),
                aliPayConfig.getCharset(),
                aliPayConfig.getAlipayPublicKey(),
                aliPayConfig.getSignType()
        );

        AlipayTradeAdvanceConsultRequest request = new AlipayTradeAdvanceConsultRequest();
        request.setBizContent(aliPayVo.toString());

        try{
            AlipayTradeAdvanceConsultResponse response = alipayClient.pageExecute(request);
            if(response.isSuccess()){
                System.out.println(response.getBody());
                // return response.getBody();
            }
        }catch (AlipayApiException e){
            throw new MFMException(201,"支付失败");
        }
        return null;
    }*/
}
