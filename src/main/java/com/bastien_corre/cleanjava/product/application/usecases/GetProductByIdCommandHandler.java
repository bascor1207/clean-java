package com.bastien_corre.cleanjava.product.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.core.infra.spring.domain.exceptions.NotFoundException;
import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;
import com.bastien_corre.cleanjava.product.domain.viewmodel.ProductViewModel;

public class GetProductByIdCommandHandler implements Command.Handler<GetProductByIdCommand, ProductViewModel> {
    private final ProductRepository productRepository;

    public GetProductByIdCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductViewModel handle(GetProductByIdCommand getProductByIdCommand) {
        var product = this.productRepository.findById(getProductByIdCommand.id()).orElseThrow(
                () -> new NotFoundException("Product", getProductByIdCommand.id())
        );

        return new ProductViewModel(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }
}
