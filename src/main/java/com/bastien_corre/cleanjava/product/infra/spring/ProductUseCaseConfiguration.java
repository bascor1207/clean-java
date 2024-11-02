package com.bastien_corre.cleanjava.product.infra.spring;

import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;
import com.bastien_corre.cleanjava.product.application.usecases.ChangeProductDescriptionCommandHandler;
import com.bastien_corre.cleanjava.product.application.usecases.CreateProductCommandHandler;
import com.bastien_corre.cleanjava.product.application.usecases.DeleteProductCommandHandler;
import com.bastien_corre.cleanjava.product.application.usecases.GetProductByIdCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductUseCaseConfiguration {
    @Bean
    public CreateProductCommandHandler CreateProductCommandHandler(ProductRepository productRepository) {
        return new CreateProductCommandHandler(productRepository);
    }

    @Bean
    public ChangeProductDescriptionCommandHandler ChangeProductDescriptionCommandHandler(ProductRepository productRepository) {
        return new ChangeProductDescriptionCommandHandler(productRepository);
    }

    @Bean
    public DeleteProductCommandHandler DeleteProductCommandHandler(ProductRepository productRepository) {
        return new DeleteProductCommandHandler(productRepository);
    }

    @Bean
    public GetProductByIdCommandHandler GetProductByIdCommandHandler(ProductRepository productRepository) {
        return new GetProductByIdCommandHandler(productRepository);
    }
}
