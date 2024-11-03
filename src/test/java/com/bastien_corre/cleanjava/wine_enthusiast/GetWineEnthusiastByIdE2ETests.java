package com.bastien_corre.cleanjava.wine_enthusiast;

import com.bastien_corre.cleanjava.PostgreSQLContainerTests;
import com.bastien_corre.cleanjava.wine_enthusiast.application.ports.WineEnthusiastRepository;
import com.bastien_corre.cleanjava.wine_enthusiast.domain.model.WineEnthusiast;
import com.bastien_corre.cleanjava.wine_enthusiast.domain.viewmodel.WineEnthusiastViewModel;
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

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLContainerTests.class)
@Transactional
public class GetWineEnthusiastByIdE2ETests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WineEnthusiastRepository wineEnthusiastRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_get_the_wine_enthusiast_by_its_id() throws Exception {
        var existingWineEnthusiast = new WineEnthusiast("123", "John", "Wick", List.of("red", "white"));
        wineEnthusiastRepository.save(existingWineEnthusiast);

        var result = mockMvc
                .perform(MockMvcRequestBuilders.get("/wineEnthusiasts/" + existingWineEnthusiast.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var wineEnthusiastViewModel = objectMapper.readValue(result.getResponse().getContentAsString(), WineEnthusiastViewModel.class);

        Assertions.assertEquals(existingWineEnthusiast.getId(), wineEnthusiastViewModel.getId());
        Assertions.assertEquals(existingWineEnthusiast.getFirstName(), wineEnthusiastViewModel.getFirstName());
        Assertions.assertEquals(existingWineEnthusiast.getLastName(), wineEnthusiastViewModel.getLastName());
        Assertions.assertEquals(List.of("red", "white"), wineEnthusiastViewModel.getPreferredWines());
    }

    @Test
    void should_fails_when_product_does_not_exist() throws Exception {
        var nonExistentProductId = "12345";

        mockMvc
                .perform(MockMvcRequestBuilders.get("/wineEnthusiasts/" + nonExistentProductId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
