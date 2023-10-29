package com.uit.cart_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.uit.cart_service.dto.ProductDto;

@FeignClient("product-service")

public interface ProductFeignClient {
     @GetMapping("/validate/{id}")
     public boolean validateProduct(@PathVariable("id") String id);
     @GetMapping("/get/{id}")
    public ProductDto getProduct(@PathVariable("id") String id);

    
}
 