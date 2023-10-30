package com.uit.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
        serverHttpSecurity.authorizeExchange(exchanges->exchanges.pathMatchers(HttpMethod.GET).permitAll()
        .pathMatchers("/api/v1/cart-service/**").hasRole("cart_role")
        ).oauth2ResourceServer(oauth2ResourceServer->oauth2ResourceServer.jwt(jwt->jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor())));
        serverHttpSecurity.csrf(csrf->csrf.disable());
        return serverHttpSecurity.build();

    }
    private Converter<Jwt,Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor(){
        JwtAuthenticationConverter jwtAuthenticationConverter=new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakRoleConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}
