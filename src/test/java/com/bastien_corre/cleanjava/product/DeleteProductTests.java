package com.bastien_corre.cleanjava.product;

import com.bastien_corre.cleanjava.core.domain.exceptions.NotFoundException;
import com.bastien_corre.cleanjava.product.application.usecases.DeleteProductCommand;
import com.bastien_corre.cleanjava.product.application.usecases.DeleteProductCommandHandler;
import com.bastien_corre.cleanjava.product.domain.model.Product;
import com.bastien_corre.cleanjava.product.infra.adapters.in_memory.InMemoryProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteProductTests {

    private final InMemoryProductRepository productRepository = new InMemoryProductRepository();

    private DeleteProductCommandHandler createCommandHandler() {
       return new DeleteProductCommandHandler(productRepository);
    }

    @Test
    public void should_delete_the_product() {
        var product = new Product("123", "Rouget", "Notes intenses de bois", 100F);
        productRepository.save(product);

        var command = new DeleteProductCommand(product.getId());
        var commandHandler = createCommandHandler();

        commandHandler.handle(command);

        var productQuery = productRepository.findById(product.getId());
        Assertions.assertTrue(productQuery.isEmpty());
    }

    @Test
    public void should_throw_not_found_when_product_does_not_exist() {
        var command = new DeleteProductCommand("nonExistentId");
        var commandHandler = createCommandHandler();

        var exception = Assertions.assertThrows(NotFoundException.class, () -> commandHandler.handle(command));

        Assertions.assertEquals("Product with key nonExistentId was not found", exception.getMessage());
    }
}
