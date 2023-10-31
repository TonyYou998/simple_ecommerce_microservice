package com.uit.cart_service.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uit.cart_service.dto.CartItemDto;
import com.uit.cart_service.dto.ProductDto;
import com.uit.cart_service.entity.Cart;
import com.uit.cart_service.entity.CartItem;
import com.uit.cart_service.feign.ProductFeignClient;
import com.uit.cart_service.repository.CartItemRepository;
import com.uit.cart_service.service.CartItemService;
import com.uit.cart_service.service.CartService;
import com.uit.cart_service.util.CartStatus;

import feign.FeignException.FeignClientException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
@Service
// @AllArgsConstructor
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final  CartItemRepository cartItemRepository;

    private CartService cartService;
    private  final ProductFeignClient productFeignClient;
    private final Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setCartService(CartService cartService){
        this.cartService=cartService;
    }
  
    @Override
    public CartItem findByCartAndProductId(Cart cart, long productId) {
        // TODO Auto-generated method stub
            CartItem cartItem=cartItemRepository.findByCartAndProductId(cart, productId);
            return cartItem;
    }

    @Override
    public void save(CartItem cartItem,Cart cart,String id) {
        // TODO Auto-generated method stub
        if(cartItem!=null){
             cartItem.setQuantity(cartItem.getQuantity()+1);
             
            cart.setStatus(CartStatus.PENDING.toString());

        }
        else{
            cartItem=new CartItem();
            cartItem.setCart(cart);
            cartItem.setProductId(Long.parseLong(id));
            cartItem.setQuantity(1L);
            cartItem.setCart(cart);

            cart.setStatus(CartStatus.PENDING.toString());
        }
        cartItemRepository.save(cartItem);
    }

    @Override
    public List<CartItemDto> findCartItemByUserId(String userId) {
        // TODO Auto-generated method stub
        // validate if user exist

        // end validate
        try {
               Cart cart=cartService.findCartByUserId(userId);
        List<CartItemDto> cartItemDtos=new ArrayList<>();

        if(cart!=null){
            List<CartItem> listCartItem=cartItemRepository.findByCart(cart);
            for(CartItem item:listCartItem){
                CartItemDto cartItemDto=new CartItemDto();
                cartItemDto.setCartId(item.getId().toString());
                cartItemDto.setId(item.getId().toString());
                ProductDto productDto=productFeignClient.getProduct(item.getProductId().toString());
                cartItemDto.setProductName(productDto.getName());
                cartItemDto.setImageUrl(productDto.getImageUrl());
                cartItemDto.setProductId(productDto.getId());
                cartItemDto.setQuantity(item.getQuantity().toString());
                cartItemDto.setPrice(productDto.getPrice());
                cartItemDtos.add(cartItemDto);

            }
            return cartItemDtos;
        }
        } catch (NumberFormatException e) {
            // TODO: handle exception
            logger.debug(e.getMessage());
            throw new NumberFormatException();
        }
        catch(FeignClientException e){
            logger.debug(e.getMessage());
            
        }
     

        return null;
    }

    @Override
    public CartItemDto increaseItem(CartItemDto dto) {
        // TODO Auto-generated method stub
        try {
            CartItem cartItem=cartItemRepository.findById(Long.parseLong(dto.getCartId())).get();
            if(cartItem!=null){
                cartItem.setQuantity(Long.parseLong(dto.getQuantity())+1);
                cartItemRepository.save(cartItem);
                CartItemDto cartItemDto=new CartItemDto();
                cartItemDto.setCartId(cartItem.getCart().getId().toString());
                cartItemDto.setQuantity(cartItem.getQuantity().toString());
                return cartItemDto;
                
            }

        } catch (NumberFormatException ex) {
            // TODO: handle exception
            logger.debug(ex.getMessage());
            throw new NumberFormatException();
        }
        
        return null;
    }

    @Override
    public CartItemDto removeItem(CartItemDto dto) {
        // TODO Auto-generated method stub
         try {
            CartItem cartItem=cartItemRepository.findById(Long.parseLong(dto.getCartId())).get();
            if(cartItem!=null){
                if(Long.parseLong(dto.getQuantity())==0)
                    return null;
                cartItem.setQuantity(Long.parseLong(dto.getQuantity())-1);
                cartItemRepository.save(cartItem);
                CartItemDto cartItemDto=new CartItemDto();
                cartItemDto.setCartId(cartItem.getCart().getId().toString());
                cartItemDto.setQuantity(cartItem.getQuantity().toString());
                return cartItemDto;
                
            }

        } catch (NumberFormatException ex) {
            // TODO: handle exception
            logger.debug(ex.getMessage());
            throw new NumberFormatException();
        }
        
        return null;
    }

    @Override
    public List<CartItemDto> getcartItemByCart(String cartId) {
        // TODO Auto-generated method stub
       try {
            Cart cart=cartService.findCartById(cartId);
            List<CartItem> listCartItems=cartItemRepository.findByCart(cart);
            List<CartItemDto> listCartItemDtos=new ArrayList<>();
            for(CartItem cartItem :listCartItems){
                    CartItemDto cartItemDto=new CartItemDto();
                    cartItemDto.setCartId(cartItem.getCart().getId().toString());
                    cartItemDto.setId(cartItem.getId().toString());
                    cartItemDto.setQuantity(cartItem.getQuantity().toString());
                    listCartItemDtos.add(cartItemDto);
            
            }
            cartService.updateStatus(cart,CartStatus.DONE);
            return listCartItemDtos;
       } catch (NumberFormatException ex) {
        // TODO: handle exception
        throw new NumberFormatException();
       }
    }
   

    
}
