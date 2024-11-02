package com.bastien_corre.cleanjava.product;

import com.bastien_corre.cleanjava.PostgreSQLContainerTests;
import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;
import com.bastien_corre.cleanjava.product.domain.model.Product;
import com.bastien_corre.cleanjava.product.infra.spring.ChangeProductDescriptionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLContainerTests.class)
public class DeleteProductE2ETests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_delete_product_given_the_id() throws Exception {
        var existingProduct = new Product("123", "Rouget", "Notes intenses de bois", 100);
        productRepository.save(existingProduct);

        mockMvc
                .perform(MockMvcRequestBuilders.delete("/products/" + existingProduct.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

       var productQuery = productRepository.findById(existingProduct.getId());
        Assertions.assertTrue(productQuery.isEmpty());
    }

    @Test
    void should_fails_when_product_does_not_exist() throws Exception {
        var nonExistentProductId = "12345";

        mockMvc
                .perform(MockMvcRequestBuilders.delete("/products/" + nonExistentProductId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
