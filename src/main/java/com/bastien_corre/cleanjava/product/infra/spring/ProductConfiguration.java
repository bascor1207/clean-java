package com.bastien_corre.cleanjava.product.infra.spring;

import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;
import com.bastien_corre.cleanjava.product.infra.adapters.jpa.SQLProductDataAccessor;
import com.bastien_corre.cleanjava.product.infra.adapters.jpa.SQLProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfiguration {

    @Bean
    ProductRepository productRepository(SQLProductDataAccessor sqlProductDataAccessor) {
        return new SQLProductRepository(sqlProductDataAccessor);
    }
}
