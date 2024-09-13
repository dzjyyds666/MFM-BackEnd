package com.Aaron.MFM.web.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication(scanBasePackages = "com.Aaron.MFM")
@MapperScan("com.Aaron.MFM.web.app.mapper")
public class AppApplication {

    public static void main(String[] args){
        SpringApplication.run(AppApplication.class, args);
    }
}
