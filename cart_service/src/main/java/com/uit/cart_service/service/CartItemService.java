package com.uit.cart_service.service;

import java.util.List;

import com.uit.cart_service.dto.CartItemDto;
import com.uit.cart_service.entity.Cart;
import com.uit.cart_service.entity.CartItem;

public interface CartItemService {

    CartItem findByCartAndProductId(Cart cart, long parseLong);

    void save(CartItem cartItem,Cart cart,String id);

    

    List<CartItemDto> findCartItemByUserId(String userId);

    CartItemDto increaseItem(CartItemDto dto);

    CartItemDto removeItem(CartItemDto dto);

    List<CartItemDto> getcartItemByCart(String cartId);

   
    
}
