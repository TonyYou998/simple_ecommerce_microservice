package com.uit.cart_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class DemoController {
    @GetMapping("/demo")
    public Object demo(){
        return  "Cart service";
    }
}
