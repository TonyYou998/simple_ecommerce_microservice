package com.uit.cart_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uit.cart_service.dto.CartItemDto;

import com.uit.cart_service.helper.ResponseHandler;
import com.uit.cart_service.service.CartItemService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CartItemController {
    private CartItemService cartItemService;
    @GetMapping("/cart/{userId}")
    public ResponseEntity<Object> getCartItems(@PathVariable("userId") String userId){
        List<CartItemDto> listCartItems=cartItemService.findCartItemByUserId(userId); 
        return ResponseHandler.getResponse(listCartItems, HttpStatus.OK);
    }
    @PostMapping("/cart/add-item")
    public ResponseEntity<Object> increaseItem(@RequestBody CartItemDto dto){
        CartItemDto cartItemDto=cartItemService.increaseItem(dto);
        return ResponseHandler.getResponse(cartItemDto, HttpStatus.OK);

    }
     @PostMapping("/cart/remove-item")
    public ResponseEntity<Object> removeItem(@RequestBody CartItemDto dto){
        CartItemDto cartItemDto=cartItemService.removeItem(dto);
        return ResponseHandler.getResponse(cartItemDto, HttpStatus.OK);

    }
    @GetMapping("/get-cart-by-cart-id/{cartId}")
    public List<CartItemDto> invokeCheckout(@PathVariable("cartId") String cartId){
        List<CartItemDto> listCartItem=cartItemService.getcartItemByCart(cartId);
        return listCartItem;

    } 
}
