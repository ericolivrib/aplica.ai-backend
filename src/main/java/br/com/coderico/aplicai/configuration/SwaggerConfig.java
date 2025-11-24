package br.com.coderico.aplicai.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI openAPI() {
        var components = new Components()
                .addSecuritySchemes("Bearer", createSecurityScheme());

        var securityRequirement = new SecurityRequirement()
                .addList("Bearer", Arrays.asList("read", "write"));

        return new OpenAPI()
                .components(components)
                .addSecurityItem(securityRequirement);
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);
    }
}
