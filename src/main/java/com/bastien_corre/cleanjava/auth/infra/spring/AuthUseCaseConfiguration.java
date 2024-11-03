package com.bastien_corre.cleanjava.auth.infra.spring;

import com.bastien_corre.cleanjava.auth.application.ports.UserRepository;
import com.bastien_corre.cleanjava.auth.application.usecases.RegisterCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthUseCaseConfiguration {
    @Bean
    public RegisterCommandHandler RegisterCommandHandler(UserRepository userRepository) {
        return new RegisterCommandHandler(userRepository);
    }
}
