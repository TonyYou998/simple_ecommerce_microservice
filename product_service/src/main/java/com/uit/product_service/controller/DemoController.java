package com.uit.product_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class DemoController {
    @GetMapping("/demo")
    public Object demo(){
        return "Product service";
    }
}
