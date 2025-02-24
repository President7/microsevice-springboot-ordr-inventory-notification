package com.ms.master.gateway.routs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
public class Routs {
    private static final Logger log = LoggerFactory.getLogger(Routs.class);

    @Bean
    RouterFunction<ServerResponse> productServiceRoute()
    {
        System.out.println("Inside this product ");
     return route("product_servcie")
             .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8080"))
            // .route(RequestPredicates.path("/api/product/id"), HandlerFunctions.http("http://localhost:8080"))
             .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
             .build();
    }
@Bean
    RouterFunction<ServerResponse> productServiceSwaggerRoute()
    {
     return route("product_servie_swagger")
             .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"),
                     HandlerFunctions.http("http://localhost:8080"))
             .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
             .filter(setPath("/api-docs")).build();
    }

@Bean
    RouterFunction<ServerResponse> orderServiceRoute()
    {
     return route("order_service")
             .route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8081"))
             .filter(CircuitBreakerFilterFunctions.circuitBreaker("orderServiceCircuitBreacker", URI.create("forward:/fallbackRoute")))

             .build();
    }
@Bean
RouterFunction<ServerResponse> orderServiceSwaggerRoute()
{
    return route("order_service_swagger")
            .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"),HandlerFunctions.http("http://localhost:8081"))
            .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
            .filter(setPath("/api-docs")).build();
}

@Bean
    RouterFunction<ServerResponse> inventoryServiceRoute()
    {
     return route("inventory_service")
             .route(RequestPredicates.path("/api/inventory"),HandlerFunctions.http("http://localhost:8082"))
             .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventoryServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
             .build();
    }
@Bean
RouterFunction<ServerResponse>    inventoryServiceSwaggerRoute()
{
return route("inventory_service")
        .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"),HandlerFunctions.http("http://localhost:8082"))
        .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventoryServiceSwaggerCircuitBreaker", URI.create("forward:/fallbackRoute")))
        .filter(setPath("/api-docs")).build();
}

@Bean
public RouterFunction<ServerResponse> fallBackRoute()
{
    System.out.println("inside this --> Route ");
    return  route("fallbackRoute")
            .GET("/fallbackRoute" ,request-> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Service Unavailable, Please try again later")).build();
}


}
