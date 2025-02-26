package com.ms.master.inventroy.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryConfigAPI {


    @Bean
    public OpenAPI productServiceAPI()
    {
        return new OpenAPI().info(new Info().description("This is a Rest API for Inventory sevrice Info")
                .title("Inventory Service API")
                .version("v.0.3")
                .license(new License().name("Apache 2.0"))).externalDocs(
                new ExternalDocumentation()
                        .description("You can refer product service wiki documentation")
                        .url("https://InventoryService-dummy-url.com/docs")
        );
    }
}
