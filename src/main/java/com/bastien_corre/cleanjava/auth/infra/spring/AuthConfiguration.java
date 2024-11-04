package com.bastien_corre.cleanjava.auth.infra.spring;

import com.bastien_corre.cleanjava.auth.application.ports.UserRepository;
import com.bastien_corre.cleanjava.auth.application.services.password_hasher.BcryptPasswordHasher;
import com.bastien_corre.cleanjava.auth.application.services.password_hasher.PasswordHasher;
import com.bastien_corre.cleanjava.auth.infra.adapters.jpa.SQLUserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfiguration {
    @Bean
    public UserRepository userRepository(EntityManager entityManager) {
        return new SQLUserRepository(entityManager);
    }

    @Bean
    public PasswordHasher passwordHasher() {
        return new BcryptPasswordHasher();
    }
}
