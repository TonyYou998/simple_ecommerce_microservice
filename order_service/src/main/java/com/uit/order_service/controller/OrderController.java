package com.uit.order_service.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uit.order_service.dto.OrderDto;
import com.uit.order_service.helper.ResponseHandler;
import com.uit.order_service.service.OrderService;


@RestController
public class OrderController {
    private OrderService orderService;
    @PostMapping("/checkout")
    public ResponseEntity<Object> checkout(@RequestBody OrderDto dto){
        
            OrderDto orderDto=orderService.checkout(dto);
            return ResponseHandler.getResponse(orderDto, HttpStatus.OK);
    }
    
}
