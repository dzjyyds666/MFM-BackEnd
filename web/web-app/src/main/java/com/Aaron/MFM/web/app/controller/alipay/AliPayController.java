package com.Aaron.MFM.web.app.controller.alipay;


import com.Aaron.MFM.common.result.Result;
import com.Aaron.MFM.web.app.service.IAliPayService;
import com.Aaron.MFM.web.app.vo.pay.AliPayVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/alipay")
@Tag(name = "支付宝支付")
public class AliPayController {


    @Autowired
    private IAliPayService aliPayService;

    @PostMapping
    @Operation(summary = "支付宝支付")
    public Result<String> alipay(@RequestBody AliPayVo payinfo)
    {
        String result =  aliPayService.payForOrder(payinfo);
        return Result.ok();
    }
}
