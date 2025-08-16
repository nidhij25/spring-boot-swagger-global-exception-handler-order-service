package com.example.orderservice.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
  @Bean
  public OpenAPI orderServiceOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Order Service API")
            .description("Spring Boot + JPA + H2 example for e-commerce orders")
            .version("v1"))
        .externalDocs(new ExternalDocumentation()
            .description("Swagger UI")
            .url("/swagger-ui/index.html"));
  }
}
