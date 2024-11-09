package com.bastien_corre.cleanjava.auth.infra.spring;

import an.awesome.pipelinr.Pipeline;
import com.bastien_corre.cleanjava.auth.application.usecases.RegisterCommand;
import com.bastien_corre.cleanjava.product.domain.viewmodel.IdResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final Pipeline pipeline;

    public AuthController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping("/register")
    public ResponseEntity<IdResponse> register(@Valid @RequestBody RegisterDTO registerDTO) {
        IdResponse result = this.pipeline.send(new RegisterCommand(registerDTO.getEmailAddress(), registerDTO.getPassword()));

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
