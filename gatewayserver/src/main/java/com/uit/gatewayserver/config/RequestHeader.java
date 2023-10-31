package com.uit.gatewayserver.config;

import org.apache.hc.core5.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
@Component
public class RequestHeader  extends AbstractGatewayFilterFactory<RequestHeader.Config>{
    
    private final Logger LOGGER= LoggerFactory.getLogger(this.getClass());
    public RequestHeader(WebClient.Builder webClientBuilder) {
        super(Config.class);

      
    }

    @Override
    public GatewayFilter apply(Config config) {


            return (exchange,chain)->{

                if( !exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                    throw new RuntimeException("Missing authorization information");
                    return exchange.getResponse().setComplete();

                }

                String authHeader= exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                LOGGER.info(authHeader);
                String [] parts=authHeader.split(" ");
                if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                    throw new RuntimeException("Incorrect authorization structure");
                }
                return null;
              
            };



    }

    public static class Config {
        // empty class as I don't need any particular configuration
    }
}
