package com.uit.cart_service.controller;
import lombok.AllArgsConstructor;

import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uit.cart_service.dto.ProductDto;
import com.uit.cart_service.helper.ResponseHandler;
import com.uit.cart_service.service.CartService;


@AllArgsConstructor
@RestController

public class CartController {
    private CartService cartService;
    @PostMapping("/add-product")
    public ResponseEntity<Object> addProductToCart(@RequestBody ProductDto dto){
            String message=cartService.addProductToCart(dto);

            return ResponseHandler.getResponse(message, HttpStatus.OK);
        
    }

    
}
