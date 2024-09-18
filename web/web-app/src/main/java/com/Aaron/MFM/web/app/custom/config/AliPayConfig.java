package com.Aaron.MFM.web.app.custom.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Data
@ConditionalOnProperty(name = "alipay.enable", havingValue = "true") // 配置文件中开启alipay
public class AliPayConfig {


    @Value("${alipay.app-id}")
    private String appId;

    @Value("${alipay.merchant-private-key}")
    private String merchantPrivateKey;

    @Value("${alipay.alipay-public-key}")
    private String alipayPublicKey;

    @Value("${alipay.server-url}")
    private String serverUrl;

    @Value("${alipay.format}")
    private String format;

    @Value("${alipay.charset}")
    private String charset;

    @Value("${alipay.sign-type}")
    private String signType;


}
