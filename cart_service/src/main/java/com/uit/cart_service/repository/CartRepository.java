package com.uit.cart_service.repository;

import com.uit.cart_service.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByCustomerId(String userId);

    Cart findByCustomerIdAndStatus(String userId, String string);

   
}
