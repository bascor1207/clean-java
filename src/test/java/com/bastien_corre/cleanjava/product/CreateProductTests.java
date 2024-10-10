package com.bastien_corre.cleanjava.product;

import com.bastien_corre.cleanjava.product.application.usecases.CreateProductCommand;
import com.bastien_corre.cleanjava.product.application.usecases.CreateProductCommandHandler;
import com.bastien_corre.cleanjava.product.domain.model.Product;
import com.bastien_corre.cleanjava.product.infra.adapters.in_memory.InMemoryProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateProductTests {
    @Test
    void should_create_product() {
        var repository = new InMemoryProductRepository();
        var useCase = new CreateProductCommandHandler(repository);

        var command = new CreateProductCommand("Rouget", "Notes intenses de bois", 100);
        var idResponse = useCase.handle(command);

        var expectedProduct = new Product("id", "Rouget", "Notes intenses de bois", 100);
        Product actualProduct = repository.findById(idResponse.getId());
        Assertions.assertEquals(expectedProduct.getName(), actualProduct.getName());
    }

}