package com.arpon007.EcommerceProject.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        SecurityScheme brearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
        SecurityRequirement brearerRequirement = new SecurityRequirement()
                .addList("Bearer Auth");
        return new OpenAPI()
                .info(new Info()
                        .title("Spring-Boot E-Commerce API")
                        .version("1.0")
                        .description("Professional E-Commerce REST API built with Spring Boot")
                        .license(new License().name("Apache 2.0").url("http://arpon007.me"))
                        .contact(new Contact()
                                .name("Md Shazan Mahmud Arpon")
                                .email("arponarpon007@gmail.com")
                                .url("http://arpon007.me")
                        )
                ).externalDocs(new ExternalDocumentation()
                        .description("API Documentation")
                .url("https://github.com/arpondark/EcommerceProject-backend-Spring-boot"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Auth", brearerScheme))
                .addSecurityItem(brearerRequirement);
    }
}
