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

    @Bean
    public GroupedOpenApi UserApi() {
        return GroupedOpenApi.builder()
                .group("app用户系统")
                .pathsToMatch("/app/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi FoodApi() {
        return GroupedOpenApi.builder()
                .group("app食物系统")
                .pathsToMatch("/app/food/**")
                .build();
    }

    @Bean
    public GroupedOpenApi CommentApi() {
        return GroupedOpenApi.builder()
                .group("app评论系统")
                .pathsToMatch("/app/comment/**")
                .build();
    }

    @Bean
    public GroupedOpenApi OrderApi() {
        return GroupedOpenApi.builder()
                .group("app订单系统")
                .pathsToMatch("/app/order/**")
                .build();
    }

    @Bean
    public GroupedOpenApi AdminApi() {
        return GroupedOpenApi.builder()
                .group("app促销系统")
                .pathsToMatch("/app/salePromotion/**")
                .build();
    }

    @Bean
    public GroupedOpenApi FileApi() {
        return GroupedOpenApi.builder()
                .group("app文件系统")
                .pathsToMatch("/app/file/**")
                .build();
    }

    @Bean
    public GroupedOpenApi ChatApi() {
        return GroupedOpenApi.builder()
                .group("app聊天系统")
                .pathsToMatch("/app/chat/**")
                .build();
    }
}
