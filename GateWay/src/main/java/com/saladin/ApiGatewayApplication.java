package com.saladin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // User Service routes
                .route("user-service", r -> r
                        .path("/auth/**", "/api/users/**")
                        .uri("lb://USER-SERVICE"))

                // Event Service routes - CORRIGÉ avec le bon nom
                .route("event-service", r -> r
                        .path("/api/events/**")
                        .filters(f -> f
                                .removeRequestHeader("Cookie")
                                .addRequestHeader("X-Forwarded-Prefix", "/api/events"))
                        .uri("lb://EVENT-DB"))  // Changé de EVENT-SERVICE à EVENT-DB

                .build();
    }
}