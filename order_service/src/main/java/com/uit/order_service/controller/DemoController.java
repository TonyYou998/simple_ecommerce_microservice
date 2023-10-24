package com.uit.order_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class DemoController {
    @GetMapping("/demo")
    public Object demoController(){
        return "Order service";
    }
}
