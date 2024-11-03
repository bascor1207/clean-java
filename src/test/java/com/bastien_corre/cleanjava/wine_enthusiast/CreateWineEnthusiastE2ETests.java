package com.bastien_corre.cleanjava.wine_enthusiast;

import com.bastien_corre.cleanjava.PostgreSQLContainerTests;
import com.bastien_corre.cleanjava.product.domain.viewmodel.IdResponse;
import com.bastien_corre.cleanjava.wine_enthusiast.application.ports.WineEnthusiastRepository;
import com.bastien_corre.cleanjava.wine_enthusiast.infra.spring.CreateWineEnthusiastDTO;
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

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLContainerTests.class)
@Transactional
public class CreateWineEnthusiastE2ETests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WineEnthusiastRepository wineEnthusiastRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_create_wineEnthusiast() throws Exception {
        var dto = new CreateWineEnthusiastDTO("John", "Doe", List.of("red", "white"));

        var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/wineEnthusiasts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        var idResponse = objectMapper.readValue(result.getResponse().getContentAsString(), IdResponse.class);
        var wineEnthusiast = wineEnthusiastRepository.findById(idResponse.getId()).get();

        Assertions.assertNotNull(wineEnthusiast);
        Assertions.assertEquals(dto.getFirstName(), wineEnthusiast.getFirstName());
        Assertions.assertEquals(dto.getLastName(), wineEnthusiast.getLastName());
        Assertions.assertEquals(dto.getPreferredWines(), wineEnthusiast.getPreferredWines());
    }

    @Test
    void should_fails_on_creating_wineEnthusiast_with_empty_or_null_personalInfos() throws Exception {
        // First Command to test null and empty firstName
        var dto = new CreateWineEnthusiastDTO("", "Doe", List.of("red", "white"));

        var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/wineEnthusiasts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();


        dto = new CreateWineEnthusiastDTO(null, "Doe", List.of("red", "white"));

        mockMvc
                .perform(MockMvcRequestBuilders.post("/wineEnthusiasts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        //Second command to test null and empty lastName
        dto = new CreateWineEnthusiastDTO("John", "", List.of("red", "white"));

        mockMvc
                .perform(MockMvcRequestBuilders.post("/wineEnthusiasts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        dto = new CreateWineEnthusiastDTO("John", null, List.of("red", "white"));

        mockMvc
                .perform(MockMvcRequestBuilders.post("/wineEnthusiasts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
