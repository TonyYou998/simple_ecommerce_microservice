package com.uit.cart_service.service;

import com.uit.cart_service.dto.ProductDto;
import com.uit.cart_service.entity.Cart;

public interface CartService {

    String addProductToCart(ProductDto dto);

    Cart findCartByUserId(String userId);

    
}
