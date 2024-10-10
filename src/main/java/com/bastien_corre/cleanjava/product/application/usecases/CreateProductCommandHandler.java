package com.bastien_corre.cleanjava.product.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.product.application.ports.ProductRepositoryInterface;
import com.bastien_corre.cleanjava.product.domain.model.Product;
import com.bastien_corre.cleanjava.product.domain.viewmodel.IdResponse;

import java.util.UUID;

public class CreateProductCommandHandler implements Command.Handler<CreateProductCommand, IdResponse> {
    private final ProductRepositoryInterface productRepositoryInterface;

    public CreateProductCommandHandler(ProductRepositoryInterface productRepositoryInterface) {
        this.productRepositoryInterface = productRepositoryInterface;
    }

    @Override
    public IdResponse handle(CreateProductCommand command) {
        var product = new Product(
                UUID.randomUUID().toString(),
                command.getProductName(),
                command.getProductDescription(),
                command.getProductPrice()
        );
        this.productRepositoryInterface.save(product);

        return new IdResponse(product.getId());
    }
}
