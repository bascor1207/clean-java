package com.bastien_corre.cleanjava.product.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;
import com.bastien_corre.cleanjava.product.domain.model.Product;
import com.bastien_corre.cleanjava.product.domain.viewmodel.IdResponse;

import java.util.UUID;

public class CreateProductCommandHandler implements Command.Handler<CreateProductCommand, IdResponse> {
    private final ProductRepository productRepository;

    public CreateProductCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public IdResponse handle(CreateProductCommand command) {
        var product = new Product(
                UUID.randomUUID().toString(),
                command.productName(),
                command.productDescription(),
                command.productPrice()
        );
        this.productRepository.save(product);

        return new IdResponse(product.getId());
    }
}
