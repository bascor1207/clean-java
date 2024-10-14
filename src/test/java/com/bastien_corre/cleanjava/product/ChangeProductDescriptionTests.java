package com.bastien_corre.cleanjava.product;

import com.bastien_corre.cleanjava.product.application.usecases.ChangeProductDescriptionCommand;
import com.bastien_corre.cleanjava.product.application.usecases.ChangeProductDescriptionCommandHandler;
import com.bastien_corre.cleanjava.product.domain.model.Product;
import com.bastien_corre.cleanjava.product.infra.adapters.in_memory.InMemoryProductRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ChangeProductDescriptionTests {

    @Test
    public void should_change_product_description() {
        var productRepository = new InMemoryProductRepository();
        var product = new Product("123", "Rouget", "Notes intenses de bois", 100);
        productRepository.save(product);

        var command = new ChangeProductDescriptionCommand(product.getId(), "Notes fruitées, une intensité troublante de fraise des bois");
        var commandHandler = new ChangeProductDescriptionCommandHandler(productRepository);

        commandHandler.handle(command);

        Product actualProduct = productRepository.findById(product.getId());
        Assertions.assertEquals(command.description(), actualProduct.getDescription());
    }
}
