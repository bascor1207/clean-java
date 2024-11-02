package com.bastien_corre.cleanjava.product.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.core.infra.spring.domain.exceptions.NotFoundException;
import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;

public class DeleteProductCommandHandler implements Command.Handler<DeleteProductCommand, Void> {
    private final ProductRepository productRepository;

    public DeleteProductCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Void handle(DeleteProductCommand deleteProductCommand) {
        var product = productRepository.findById(deleteProductCommand.id()).orElseThrow(() -> new NotFoundException("Product", deleteProductCommand.id()));

        productRepository.delete(product);
        return null;
    }
}
