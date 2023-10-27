package com.uit.product_service.entity;

import jakarta.persistence.*;

import lombok.Data;


@Entity
@Table(name = "products")
@Data
public class Product extends BaseEntity {

    private String name;
    private String description;
    private String price;
    @Column(name="image_url")
    private String imageUrl;
}
