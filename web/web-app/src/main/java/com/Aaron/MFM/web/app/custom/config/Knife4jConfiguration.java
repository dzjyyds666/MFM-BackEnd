package com.Aaron.MFM.web.app.custom.config;

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
                        .title("MFMapp系统")
                        .version("1.0")
                        .description("MFMapp系统"));
    }

    @Bean
    public GroupedOpenApi LoginApi() {
        return GroupedOpenApi.builder()
                .group("app登录系统")
                .pathsToMatch("/app/login/**")
                .build();
    }
}
