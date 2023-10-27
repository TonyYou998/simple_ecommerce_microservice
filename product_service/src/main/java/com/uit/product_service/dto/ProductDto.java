package com.uit.product_service.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private String price;
}
