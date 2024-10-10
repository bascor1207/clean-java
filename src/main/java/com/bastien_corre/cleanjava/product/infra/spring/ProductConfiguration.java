package com.bastien_corre.cleanjava.product.infra.spring;

import com.bastien_corre.cleanjava.product.application.ports.ProductRepositoryInterface;
import com.bastien_corre.cleanjava.product.infra.adapters.in_memory.InMemoryProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfiguration {

    @Bean
    ProductRepositoryInterface productRepository() {
        return new InMemoryProductRepository();
    }
}
