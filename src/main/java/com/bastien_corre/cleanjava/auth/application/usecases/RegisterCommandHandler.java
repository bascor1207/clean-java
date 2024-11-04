package com.bastien_corre.cleanjava.auth.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.auth.application.ports.UserRepository;
import com.bastien_corre.cleanjava.auth.application.services.password_hasher.PasswordHasher;
import com.bastien_corre.cleanjava.auth.domain.model.User;
import com.bastien_corre.cleanjava.product.domain.viewmodel.IdResponse;

import java.util.UUID;

public class RegisterCommandHandler implements Command.Handler<RegisterCommand, IdResponse> {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public RegisterCommandHandler(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public IdResponse handle(RegisterCommand registerCommand) {
        var user = new User(
                UUID.randomUUID().toString(),
                registerCommand.emailAddress(),
                passwordHasher.hash(registerCommand.password())
        );

        userRepository.save(user);

        return new IdResponse(user.getId());
    }
}
