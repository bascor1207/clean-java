package com.bastien_corre.cleanjava.auth;

import com.bastien_corre.cleanjava.PostgreSQLContainerTests;
import com.bastien_corre.cleanjava.auth.application.ports.UserRepository;
import com.bastien_corre.cleanjava.auth.domain.model.User;
import com.bastien_corre.cleanjava.auth.infra.spring.RegisterDTO;
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
public class RegisterE2ETests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_register_user() throws Exception {
        var dto = new RegisterDTO("john@doe.com", "password");

        var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        var idResponse = objectMapper.readValue(result.getResponse().getContentAsString(), IdResponse.class);
        var user = userRepository.findById(idResponse.getId()).get();

        Assertions.assertNotNull(user);
        Assertions.assertEquals(dto.getEmailAddress(), user.getEmailAddress());
    }

    @Test
    void when_email_already_exists_should_throw_exception() throws Exception {
        var existingUser = new User("123", "john@doe.com", "password");
        userRepository.save(existingUser);

        var dto = new RegisterDTO("john@doe.com", "password");

        mockMvc
                .perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
