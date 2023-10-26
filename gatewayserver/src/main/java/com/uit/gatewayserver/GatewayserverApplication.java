package com.uit.gatewayserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }
//    @Autowired
//    private TokenRelayGatewayFilterFactory filterFactory;

    @Bean
    public RouteLocator myRouteConfig(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/api/v1/cart-service/**")
                        .filters(f -> f.rewritePath("/api/v1/cart-service/(?<segment>.*)","/${segment}"))
                                
//                                .removeRequestHeader("Cookie"))
                        .uri("lb://CART-SERVICE"))
                .route(p -> p
                        .path("/api/v1/customer-service/**")
                        .filters(f -> f.rewritePath("/api/v1/customer-service/(?<segment>.*)","/${segment}"))

//                                .removeRequestHeader("Cookie"))
                        .uri("lb://CUSTOMER-SERVICE"))
                .route(p -> p
                        .path("/api/v1/order-service/**")
                        .filters(f -> f.rewritePath("/api/v1/order-service/(?<segment>.*)","/${segment}"))

//                                .removeRequestHeader("Cookie"))
                        .uri("lb://ORDER-SERVICE"))
                .route(p -> p
                        .path("/api/v1/product-service/**")
                        .filters(f -> f.rewritePath("/api/v1/product-service/(?<segment>.*)","/${segment}"))

//                                .removeRequestHeader("Cookie"))
                        .uri("lb://PRODUCT-SERVICE"))

//                route(p -> p
//                        .path("/eazybank/cards/**")
//                        .filters(f -> f.filters(filterFactory.apply())
//                                .rewritePath("/eazybank/cards/(?<segment>.*)","/${segment}")
//                                .removeRequestHeader("Cookie"))
//                        .uri("lb://CARDS"))
                        .build();


    }

}
