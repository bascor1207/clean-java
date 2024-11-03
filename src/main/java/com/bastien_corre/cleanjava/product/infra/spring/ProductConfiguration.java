package com.bastien_corre.cleanjava.product.infra.spring;

import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;
import com.bastien_corre.cleanjava.product.infra.adapters.jpa.SQLProductRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfiguration {

    @Bean
    ProductRepository productRepository(EntityManager entityManager) {
        return new SQLProductRepository(entityManager);
    }
}
