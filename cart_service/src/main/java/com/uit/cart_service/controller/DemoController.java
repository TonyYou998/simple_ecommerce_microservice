package com.uit.cart_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping
public class DemoController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @GetMapping("/demo")
    public Object demo(@RequestHeader("ecommerce-correlation-id") String correctlationId){
        logger.debug("CorreclationId:{}",correctlationId);
        return  "Cart service";
    }
}
