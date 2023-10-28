package com.uit.product_service.seeder;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.uit.product_service.entity.Product;
import com.uit.product_service.repository.ProductRepository;

import lombok.AllArgsConstructor;


@Component
@Transactional
@AllArgsConstructor
public class ProductSeeder {
    
    private ProductRepository productRepository;
    @EventListener
    public void seedProduct(ContextRefreshedEvent event){
        Random random = new Random();
        List<Product> lstProducts=new ArrayList<>();
            for(int i=1;i<=23;i++){
                Product product=new Product();
                product.setName("Demo"+i);
                product.setDescription("this is a demo");
                product.setImageUrl("https://picsum.photos/id/"+i+"/350/300");
                product.setPrice(String.valueOf(random.nextInt(500)));
                lstProducts.add(product);
            }
            productRepository.saveAll(lstProducts);

    }
}
