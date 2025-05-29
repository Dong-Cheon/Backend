package com.doubleowner.revibe.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String VERSION = "1.0.0";
    private static final String TITLE = "revibe 프로젝트 API 명세서";
    private static final String SECURITY_SCHEME_NAME ="Bearer Authentication";

    @Value("${springdoc.swagger-ui.url}")
    private String url;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(TITLE)
                        .version(VERSION))
                .addServersItem(new Server().url(url))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme().name(SECURITY_SCHEME_NAME).type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
