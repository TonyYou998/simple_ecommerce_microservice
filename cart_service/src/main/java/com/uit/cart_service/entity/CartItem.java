package com.uit.cart_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="cart_items")
public class CartItem extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @Column(name="product_id")
    private Long productId;
    private Long quantity;
    
}
