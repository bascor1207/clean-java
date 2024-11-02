package com.bastien_corre.cleanjava.product.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.core.infra.spring.domain.exceptions.NotFoundException;
import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;
import com.bastien_corre.cleanjava.product.domain.model.Product;

public class ChangeProductDescriptionCommandHandler implements Command.Handler<ChangeProductDescriptionCommand, Void> {
    private final ProductRepository productRepository;

    public ChangeProductDescriptionCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Void handle(ChangeProductDescriptionCommand changeProductDescriptionCommand) throws NotFoundException {
       var productQuery = this.productRepository.findById(changeProductDescriptionCommand.id());

       if (productQuery.isEmpty()) {
           throw new NotFoundException("Product", changeProductDescriptionCommand.id());
       }

       Product product = productQuery.get();
       product.changeDescription(changeProductDescriptionCommand.description());
       productRepository.save(product);
       return null;
    }
}
