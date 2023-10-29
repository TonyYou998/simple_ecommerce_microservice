package com.uit.cart_service.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private String id;
    private String cartId;
    private String productId;
    private String imageUrl;
    private String productName;
    private String quality;
}
