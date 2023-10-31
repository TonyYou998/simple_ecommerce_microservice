package com.uit.cart_service.service.impl;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import com.uit.cart_service.entity.Cart;
import com.uit.cart_service.entity.CartItem;
import com.uit.cart_service.repository.CartItemRepository;
import com.uit.cart_service.repository.CartRepository;
import feign.FeignException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.uit.cart_service.dto.ProductDto;
import com.uit.cart_service.feign.ProductFeignClient;
import com.uit.cart_service.service.CartItemService;
import com.uit.cart_service.service.CartService;
import com.uit.cart_service.util.CartStatus;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());

    private final ProductFeignClient productFeignClient;
    private  CartItemService cartItemService;
    private final CartRepository cartRepository;
    @Autowired
    public void setCartItemService(CartItemService cartItemService){
        this.cartItemService=cartItemService;

    }
   
    @Override
    public String addProductToCart(ProductDto dto) {
        // TODO Auto-generated method stub
      
        try {
            
                
            if(productFeignClient.validateProduct(dto.getId())){
               
                
                Cart cart=cartRepository.findByCustomerIdAndStatus(dto.getUserId(),CartStatus.PENDING.toString());
                if(cart==null){
                     cart=new Cart();
                     cart.setCustomerId(dto.getUserId());
                     cartRepository.save(cart);
                }
                CartItem cartItem=cartItemService.findByCartAndProductId(cart,Long.parseLong(dto.getId()));
                cartItemService.save(cartItem, cart,dto.getId());

               return "Product added successfully";
            }
        } catch (NumberFormatException e) {
            // TODO: handle exception
                logger.debug(e.getMessage());
                throw new NumberFormatException();
        }
        catch (FeignException e){
            logger.debug(e.getMessage());
            return "Cannot find the product";
        }
       return null;
       

    }
    @Override
    public Cart findCartByUserId(String userId) {
        // TODO Auto-generated method stub
       try {
        Cart cart=cartRepository.findByCustomerIdAndStatus(userId,CartStatus.PENDING.toString());
        if(cart!=null)
            return cart;
        return null;

       } catch (NumberFormatException e) {
        // TODO: handle exception
        logger.debug(e.getMessage());
        throw new NumberFormatException();
       }
    }
    
}
