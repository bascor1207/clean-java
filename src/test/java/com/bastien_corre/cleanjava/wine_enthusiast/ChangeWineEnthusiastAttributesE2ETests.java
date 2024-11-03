package com.bastien_corre.cleanjava.wine_enthusiast;

import com.bastien_corre.cleanjava.PostgreSQLContainerTests;
import com.bastien_corre.cleanjava.wine_enthusiast.application.ports.WineEnthusiastRepository;
import com.bastien_corre.cleanjava.wine_enthusiast.domain.model.WineEnthusiast;
import com.bastien_corre.cleanjava.wine_enthusiast.infra.spring.WineEnthusiastInfosUpdateDTO;
import com.bastien_corre.cleanjava.wine_enthusiast.infra.spring.WineEnthusiastPreferredWinesUpdateDTO;
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
public class ChangeWineEnthusiastAttributesE2ETests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WineEnthusiastRepository wineEnthusiastRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_change_wine_enthusiast_infos() throws Exception {
        var existingWineEnthusiast = new WineEnthusiast("123", "John", "Doe", List.of("red", "white"));
        wineEnthusiastRepository.save(existingWineEnthusiast);

        var dto = new WineEnthusiastInfosUpdateDTO("Jack", "Daniels");

        mockMvc
                .perform(MockMvcRequestBuilders.patch("/wineEnthusiasts/" + existingWineEnthusiast.getId() + "/personalInfos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertEquals(dto.getFirstName(), existingWineEnthusiast.getFirstName());
        Assertions.assertEquals(dto.getLastName(), existingWineEnthusiast.getLastName());
    }

    @Test
    void should_change_wine_enthusiast_preferred_wines() throws Exception {
        var existingWineEnthusiast = new WineEnthusiast("123", "John", "Doe", List.of("red", "white"));
        wineEnthusiastRepository.save(existingWineEnthusiast);

        var dto = new WineEnthusiastPreferredWinesUpdateDTO(List.of("white", "pink"));

        mockMvc
                .perform(MockMvcRequestBuilders.patch("/wineEnthusiasts/" + existingWineEnthusiast.getId() + "/wineRelevantInfos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertEquals(dto.getPreferredWines(), existingWineEnthusiast.getPreferredWines());
    }

    @Test
    void should_fails_when_wine_enthusiast_does_not_exist() throws Exception {
        var nonExistentWineEnthusiastId = "nonExistentId";

        mockMvc
                .perform(MockMvcRequestBuilders.patch("/wineEnthusiasts/" + nonExistentWineEnthusiastId + "/personalInfos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new WineEnthusiastInfosUpdateDTO("Jack", "Daniels"))))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        mockMvc
                .perform(MockMvcRequestBuilders.patch("/wineEnthusiasts/" + nonExistentWineEnthusiastId + "/wineRelevantInfos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new WineEnthusiastPreferredWinesUpdateDTO(List.of("white", "pink")))))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
