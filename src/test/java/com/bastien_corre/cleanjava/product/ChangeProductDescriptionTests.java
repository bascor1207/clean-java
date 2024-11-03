package com.bastien_corre.cleanjava.product;

import com.bastien_corre.cleanjava.core.domain.exceptions.NotFoundException;
import com.bastien_corre.cleanjava.product.application.usecases.ChangeProductDescriptionCommand;
import com.bastien_corre.cleanjava.product.application.usecases.ChangeProductDescriptionCommandHandler;
import com.bastien_corre.cleanjava.product.domain.model.Product;
import com.bastien_corre.cleanjava.product.infra.adapters.in_memory.InMemoryProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ChangeProductDescriptionTests {

    private final InMemoryProductRepository productRepository = new InMemoryProductRepository();

    private ChangeProductDescriptionCommandHandler createCommandHandler() {
       return new ChangeProductDescriptionCommandHandler(productRepository);
    }

    @Test
    public void should_change_product_description() {
        var product = new Product("123", "Rouget", "Notes intenses de bois", 100F);
        productRepository.save(product);

        var command = new ChangeProductDescriptionCommand(product.getId(), "Notes fruitées, une intensité troublante de fraise des bois");
        var commandHandler = createCommandHandler();

        commandHandler.handle(command);

        Product actualProduct = productRepository.findById(product.getId()).get();
        Assertions.assertEquals(command.description(), actualProduct.getDescription());
    }

    @Test
    public void should_throw_not_found_when_product_does_not_exist() {
        var command = new ChangeProductDescriptionCommand("nonExistentId", "Notes fruitées, une intensité troublante de fraise des bois");
        var commandHandler = createCommandHandler();

        var exception = Assertions.assertThrows(NotFoundException.class, () -> commandHandler.handle(command));

        Assertions.assertEquals("Product with key nonExistentId was not found", exception.getMessage());
    }
}
