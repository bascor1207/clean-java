package com.bastien_corre.cleanjava.auth;

import com.bastien_corre.cleanjava.auth.application.ports.UserRepository;
import com.bastien_corre.cleanjava.auth.application.services.password_hasher.BcryptPasswordHasher;
import com.bastien_corre.cleanjava.auth.application.services.password_hasher.PasswordHasher;
import com.bastien_corre.cleanjava.auth.application.usecases.RegisterCommand;
import com.bastien_corre.cleanjava.auth.application.usecases.RegisterCommandHandler;
import com.bastien_corre.cleanjava.auth.infra.adapters.in_memory.InMemoryUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegisterCommandHandlerTests {
    private final UserRepository userRepository = new InMemoryUserRepository();
    private final PasswordHasher passwordHasher = new BcryptPasswordHasher();

    private RegisterCommandHandler createCommandHandler() {
        return new RegisterCommandHandler(userRepository, passwordHasher);
    }

    @Test
    public void should_register_user() {
        var command = new RegisterCommand("john@doe.com", "password");
        var commandHandler = createCommandHandler();

        var response = commandHandler.handle(command);

        var actualUser = userRepository.findById(response.getId()).get();

        Assertions.assertEquals(command.emailAddress(), actualUser.getEmailAddress());
        Assertions.assertTrue(passwordHasher.matches(command.password(), actualUser.getPassword()));
    }
}
