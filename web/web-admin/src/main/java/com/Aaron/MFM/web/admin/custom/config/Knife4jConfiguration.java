package com.Aaron.MFM.web.admin.custom.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("MFM后台管理")
                        .version("1.0")
                        .description("MFM后台管理"));
    }

    @Bean
    public GroupedOpenApi adminUserApi() {
        return GroupedOpenApi.builder()
                .group("后台用户管理")
                .pathsToMatch("/admin/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi loginApi() {
        return GroupedOpenApi.builder()
                .group("后台登录管理")
                .pathsToMatch("/admin/login/**")
                .build();
    }
}
