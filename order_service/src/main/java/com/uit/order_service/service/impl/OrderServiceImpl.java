package com.uit.order_service.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.uit.order_service.dto.CartItemDto;
import com.uit.order_service.dto.OrderDto;
import com.uit.order_service.entity.Order;
import com.uit.order_service.entity.OrderItem;
import com.uit.order_service.feign.CartFeignClient;
import com.uit.order_service.repository.OrderItemReoisitory;
import com.uit.order_service.repository.OrderRepository;
import com.uit.order_service.service.OrderService;
import com.uit.order_service.util.OrderStatus;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private CartFeignClient cartFeignClient;
    private OrderRepository orderRepository;
    private OrderItemReoisitory orderItemReoisitory;
    
    @Override
    public OrderDto checkout(OrderDto dto) {
        // TODO Auto-generated method stub
        try {
            List<CartItemDto> listCart=cartFeignClient.invokeCheckout(dto.getCartId());
            Order order=new Order();
            order.setCustomerId(dto.getUserId());
            Random random=new Random();
            order.setPrice(String.valueOf(random.nextInt(1000)));
            order.setStatus(OrderStatus.DELIVERING.toString());
            List<OrderItem> orderItems=new ArrayList<>();
            
            order= orderRepository.save(order);
            for(CartItemDto item:listCart){
                    OrderItem orderItem=new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProductId(Long.parseLong(item.getProductId()));
                    orderItem.setQuantity(Long.parseLong(item.getQuantity()));
                    orderItems.add(orderItem);
            }
            orderItemReoisitory.saveAll(orderItems);
            OrderDto orderDto=new OrderDto();
            orderDto.setCartId(dto.getCartId());
            orderDto.setUserId(dto.getUserId());
            return orderDto;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
       
    }
    
}
