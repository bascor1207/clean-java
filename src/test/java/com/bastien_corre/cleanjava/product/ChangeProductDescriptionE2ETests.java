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
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLContainerTests.class)
@Transactional
public class ChangeProductDescriptionE2ETests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_change_product_description() throws Exception {
        var existingProduct = new Product("123", "Rouget", "Notes intenses de bois", 100);

        productRepository.save(existingProduct);
        var dto = new ChangeProductDescriptionDTO("Notes fruitées, une intensité troublante de fraise des bois");

        mockMvc
                .perform(MockMvcRequestBuilders.patch("/products/" + existingProduct.getId() + "/description")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

       var product = productRepository.findById(existingProduct.getId()).get();

        Assertions.assertEquals(dto.getDescription(), product.getDescription());
    }

    @Test
    void should_fails_when_product_does_not_exist() throws Exception {
        var nonExistentProductId = "12345";

        var dto = new ChangeProductDescriptionDTO("Notes fruitées, une intensité troublante de fraise des bois");

        mockMvc
                .perform(MockMvcRequestBuilders.patch("/products/" + nonExistentProductId + "/description")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }
}
