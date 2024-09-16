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

    @Bean
    public GroupedOpenApi foodApi() {
        return GroupedOpenApi.builder()
                .group("后台菜品管理")
                .pathsToMatch("/admin/food/**")
                .build();
    }

    @Bean
    public GroupedOpenApi orderApi() {
        return GroupedOpenApi.builder()
                .group("后台订单管理")
                .pathsToMatch("/admin/order/**")
                .build();
    }

    @Bean
    public GroupedOpenApi commentApi() {
        return GroupedOpenApi.builder()
                .group("后台评论管理")
                .pathsToMatch("/admin/comment/**")
                .build();
    }

    @Bean
    public GroupedOpenApi comboApi() {
        return GroupedOpenApi.builder()
                .group("后台套餐管理")
                .pathsToMatch("/admin/combo/**")
                .build();
    }

    @Bean
    public GroupedOpenApi salesPromotionApi() {
        return GroupedOpenApi.builder()
                .group("后台促销管理")
                .pathsToMatch("/admin/salesPromotion/**")
                .build();
    }

    @Bean
    public GroupedOpenApi fileApi() {
        return GroupedOpenApi.builder()
                .group("后台文件管理")
                .pathsToMatch("/admin/file/**")
                .build();
    }

    @Bean
    public GroupedOpenApi chatApi() {
        return GroupedOpenApi.builder()
                .group("后台聊天管理")
                .pathsToMatch("/admin/chat/**")
                .build();
    }
}
