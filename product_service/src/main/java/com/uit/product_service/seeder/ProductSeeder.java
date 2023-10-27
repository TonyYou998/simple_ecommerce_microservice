package com.uit.product_service.seeder;
import java.util.LinkedList;
import java.util.List;

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
        List<Product> lstProducts=new LinkedList<>();
            for(int i=0;i<100;i++){
                Product product=new Product();
                product.setName("Demo1");
                product.setDescription("this is a demo");
                product.setImageUrl("http://localhost:8084/image.jpg");
                product.setPrice("250");
                lstProducts.add(product);
            }
            productRepository.saveAll(lstProducts);

    }
}
