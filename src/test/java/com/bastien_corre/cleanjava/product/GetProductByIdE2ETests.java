package com.bastien_corre.cleanjava.product;

import com.bastien_corre.cleanjava.PostgreSQLContainerTests;
import com.bastien_corre.cleanjava.product.application.ports.ProductRepository;
import com.bastien_corre.cleanjava.product.domain.model.Product;
import com.bastien_corre.cleanjava.product.domain.viewmodel.ProductViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLContainerTests.class)
@Transactional
public class GetProductByIdE2ETests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_get_the_product_by_its_id() throws Exception {
        var existingProduct = new Product("123", "Rouget", "Notes intenses de bois", 100F);
        productRepository.save(existingProduct);

       var result = mockMvc
                .perform(MockMvcRequestBuilders.get("/products/" + existingProduct.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

       var productViewModel = objectMapper.readValue(result.getResponse().getContentAsString(), ProductViewModel.class);

        Assertions.assertEquals(existingProduct.getId(), productViewModel.getId());
        Assertions.assertEquals(existingProduct.getName(), productViewModel.getName());
        Assertions.assertEquals(existingProduct.getDescription(), productViewModel.getDescription());
        Assertions.assertEquals(existingProduct.getPrice(), productViewModel.getPrice());
    }

    @Test
    void should_fails_when_product_does_not_exist() throws Exception {
        var nonExistentProductId = "12345";

        mockMvc
                .perform(MockMvcRequestBuilders.get("/products/" + nonExistentProductId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
