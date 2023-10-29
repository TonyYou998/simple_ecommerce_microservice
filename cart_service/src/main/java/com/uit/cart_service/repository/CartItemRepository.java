package com.uit.cart_service.repository;

import com.uit.cart_service.entity.Cart;
import com.uit.cart_service.entity.CartItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    CartItem findByCartAndProductId(Cart cart, long parseLong);

    List<CartItem> findByCart(Cart cart);

   
}
