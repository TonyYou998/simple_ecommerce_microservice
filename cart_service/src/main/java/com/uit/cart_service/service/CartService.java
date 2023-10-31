package com.uit.cart_service.service;

import com.uit.cart_service.dto.ProductDto;
import com.uit.cart_service.entity.Cart;
import com.uit.cart_service.util.CartStatus;

public interface CartService {

    String addProductToCart(ProductDto dto);

    Cart findCartByUserId(String userId);

    Cart findCartById(String cartId);

    void updateStatus(CartStatus done);

    void updateStatus(Cart cart, CartStatus done);

    
}
