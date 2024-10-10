package com.bastien_corre.cleanjava.product.infra.spring;

import com.bastien_corre.cleanjava.product.application.ports.ProductRepositoryInterface;
import com.bastien_corre.cleanjava.product.application.usecases.CreateProductCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerUseCaseConfiguration {
    @Bean
    public CreateProductCommandHandler CreateProductCommandHandler(ProductRepositoryInterface productRepositoryInterface) {
        return new CreateProductCommandHandler(productRepositoryInterface);
    }
}
