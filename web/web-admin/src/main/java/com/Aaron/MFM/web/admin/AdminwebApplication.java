package com.Aaron.MFM.web.admin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.Aaron.MFM")
@MapperScan("com.Aaron.MFM.web.admin.mapper")
public class AdminwebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminwebApplication.class, args);
    }
}
