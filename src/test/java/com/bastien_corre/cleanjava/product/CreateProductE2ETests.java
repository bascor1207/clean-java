package com.bastien_corre.cleanjava.product;

import com.bastien_corre.cleanjava.PostgreSQLContainerTests;
import com.bastien_corre.cleanjava.product.application.ports.ProductRepositoryInterface;
import com.bastien_corre.cleanjava.product.domain.viewmodel.IdResponse;
import com.bastien_corre.cleanjava.product.infra.spring.CreateProductDTO;
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
public class CreateProductE2ETests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepositoryInterface productRepositoryInterface;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_product() throws Exception {
        var dto = new CreateProductDTO("Rouget", "Notes intenses de bois", 100);
        System.out.println(objectMapper.writeValueAsString(dto));

       var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

       var idResponse = objectMapper.readValue(result.getResponse().getContentAsString(), IdResponse.class);
       var product = productRepositoryInterface.findById(idResponse.getId());

        Assertions.assertNotNull(product);
        Assertions.assertEquals(dto.getProductName(), product.getName());
    }

}