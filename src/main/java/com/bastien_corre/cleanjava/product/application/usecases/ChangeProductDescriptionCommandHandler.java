package com.bastien_corre.cleanjava.product.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.core.domain.exceptions.NotFoundException;
import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;

public class ChangeProductDescriptionCommandHandler implements Command.Handler<ChangeProductDescriptionCommand, Void> {
    private final ProductRepository productRepository;

    public ChangeProductDescriptionCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Void handle(ChangeProductDescriptionCommand changeProductDescriptionCommand) throws NotFoundException {
       var product = this.productRepository.findById(changeProductDescriptionCommand.id()).orElseThrow(() -> new NotFoundException("Product", changeProductDescriptionCommand.id()));

       product.changeDescription(changeProductDescriptionCommand.description());
       productRepository.save(product);

       return null;
    }
}
