package com.uit.customer_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class DemoController {
    @GetMapping("/demo")
    public Object demoController(){
        return "Customer Service";
    }
}
