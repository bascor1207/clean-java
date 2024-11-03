package com.bastien_corre.cleanjava.wine_enthusiast;

import com.bastien_corre.cleanjava.PostgreSQLContainerTests;
import com.bastien_corre.cleanjava.wine_enthusiast.application.ports.WineEnthusiastRepository;
import com.bastien_corre.cleanjava.wine_enthusiast.domain.model.WineEnthusiast;
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

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLContainerTests.class)
@Transactional
public class DeleteWineEnthusiastE2ETests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WineEnthusiastRepository wineEnthusiastRepository;

    @Test
    void should_delete_wineEnthusiast_given_the_id() throws Exception {
        var existingWineEnthusiast = new WineEnthusiast("123", "John", "Doe", List.of("red", "white"));
        wineEnthusiastRepository.save(existingWineEnthusiast);

        mockMvc
                .perform(MockMvcRequestBuilders.delete("/wineEnthusiasts/" + existingWineEnthusiast.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        var wineEnthusiastQuery = wineEnthusiastRepository.findById(existingWineEnthusiast.getId());
        Assertions.assertTrue(wineEnthusiastQuery.isEmpty());
    }

    @Test
    void should_fails_when_wine_enthusiast_does_not_exist() throws Exception {
        var nonExistentWineEnthusiastId = "nonExistentId";

        mockMvc
                .perform(MockMvcRequestBuilders.delete("/wineEnthusiasts/" + nonExistentWineEnthusiastId))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
