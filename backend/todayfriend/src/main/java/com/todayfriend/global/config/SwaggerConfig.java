package com.todayfriend.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi memberOpenAPI() {
        return GroupedOpenApi.builder()
                .group("member")
                .pathsToMatch("/api/members/**")
                .build();
    }
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("API 명세서")
                        .description("오늘의 친구 API 명세서")
                        .version("v0.0.1"));
    }
}
