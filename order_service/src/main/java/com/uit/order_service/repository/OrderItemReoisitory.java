package com.uit.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uit.order_service.entity.OrderItem;
@Repository
public interface OrderItemReoisitory extends JpaRepository<OrderItem,Long> {
    
}
