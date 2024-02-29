package com.todayfriend.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
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
    public OpenAPI openAPI() {
        String key = "Access Token (Bearer)";
        String refreshKey = "Refresh Token";

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(key)
                .addList(refreshKey);

        SecurityScheme accessTokenSecurityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);

        SecurityScheme refreshTokenSecurityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name("Authoization-Refresh");

        Components components = new Components()
                .addSecuritySchemes(key, accessTokenSecurityScheme)
                .addSecuritySchemes(refreshKey, refreshTokenSecurityScheme);
        return new OpenAPI()
                .info(apiInfo())
                .addSecurityItem(securityRequirement)
                .components(components);
    }
    private Info apiInfo() {
        return new Info()
                .title("API 명세서") // API의 제목
                .description("오늘의 친구 API 명세서") // API에 대한 설명
                .version("1.0.0"); // API의 버전
    }

}
