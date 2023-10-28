package com.uit.product_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uit.product_service.dto.ProductDto;
import com.uit.product_service.helper.ResponseHandler;
import com.uit.product_service.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<Object> getAllProduct(){
             List<ProductDto> listDto= productService.getAllProducts();
            return ResponseHandler.getResponse(listDto, HttpStatus.OK);
            
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Object> getDetailProduct(@PathVariable("id") String id){
        ProductDto dto=productService.getPorductById(id);
        return ResponseHandler.getResponse(dto, HttpStatus.OK);
    }
    
}
