package com.uit.cart_service.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import com.uit.cart_service.util.CartStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name="carts")
public class Cart extends BaseEntity {
    @Column(name = "customer_id")
    private String customerId;
    private String status=CartStatus.EMPTY.toString();
    @OneToMany(mappedBy = "cart")
    Set<CartItem> setCartItem=new LinkedHashSet<>();

    
}
