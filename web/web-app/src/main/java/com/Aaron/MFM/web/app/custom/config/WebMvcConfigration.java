package com.Aaron.MFM.web.app.custom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.Aaron.MFM.web.app.custom.interceptor.AuthenticationInterceptor;


@Configuration
public class WebMvcConfigration implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor AnthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.AnthenticationInterceptor)
                .addPathPatterns("/app/**","/app/login/logout")
                .excludePathPatterns("/app/login/getcaptcha","/app/login","/app/login/register");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域访问的路径
                .allowedOrigins("*") // 允许跨域访问的源
                .allowedMethods("POST", "GET", "PUT", "DELETE");// 允许请求方法
    }
}
