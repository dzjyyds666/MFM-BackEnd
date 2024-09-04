package com.Aaron.MFM.web.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.Aaron.MFM")
@MapperScan("com.Aaron.MFM.web.app.mapper")
public class AppApplication {
    public static void main(String[] args){
        SpringApplication.run(AppApplication.class, args);
    }
}
