package com.uit.order_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.uit.order_service.dto.CartItemDto;



@FeignClient("cart-service")
public interface CartFeignClient {
   @GetMapping("/get-cart-by-cart-id/{cartId}")
    public List<CartItemDto> invokeCheckout(@PathVariable("cartId") String cartId);

    
}
 