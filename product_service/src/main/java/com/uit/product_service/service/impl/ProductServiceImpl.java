package com.uit.product_service.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.uit.product_service.dto.ProductDto;
import com.uit.product_service.entity.Product;
import com.uit.product_service.repository.ProductRepository;
import com.uit.product_service.service.ProductService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService  {
    private ProductRepository productRepository;
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Override
    public List<ProductDto> getAllProducts() {
        try{
            List<Product> listProduct=productRepository.findAll();
        if(listProduct.size()==0)
            return null;
        List<ProductDto> listProductDtos=new ArrayList<>();
        for(Product p:listProduct){
            ProductDto dto=new ProductDto();
            dto.setId(String.valueOf(p.getId()));
            dto.setName(p.getName());
            dto.setDescription(p.getDescription());
            dto.setPrice(p.getPrice());
            dto.setImageUrl(p.getImageUrl());
            listProductDtos.add(dto);
          
        }
          return listProductDtos;
        }
        catch(Exception e){
            logger.error(e.getMessage(), e);
           
        }
        return null;
        
    }
    @Override
    public ProductDto getPorductById(String id){
        try {
            Long productId=Long.parseLong(id);
            Product product= productRepository.findById(productId).get();
            ProductDto dto=new ProductDto();
            dto.setId(String.valueOf(product.getId()));
            dto.setDescription(product.getDescription());
            dto.setImageUrl(product.getImageUrl());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            return dto;
        } catch (NumberFormatException ex) {
        
            logger.debug(ex.getMessage());
            throw new NumberFormatException();
        }
        catch(NoSuchElementException ex){
            logger.debug(ex.getMessage());
            throw new NoSuchElementException();

        }
        
    }
}
