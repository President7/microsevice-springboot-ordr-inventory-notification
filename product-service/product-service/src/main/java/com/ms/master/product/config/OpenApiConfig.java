package com.ms.master.product.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productServiceAPI()
    {
        return new OpenAPI().info(new Info().description("This is a Rest API for Product sevrice Info")
                .title("Product Service API")
                .version("v.0.1")
                .license(new License().name("Apache 2.0"))).externalDocs(
                        new ExternalDocumentation()
                                .description("You can refer product service wiki documentation")
                                .url("https://productService-dummy-url.com/docs")
        );
    }
}
