package com.bastien_corre.cleanjava.auth.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.product.domain.viewmodel.IdResponse;

public record RegisterCommand(String emailAddress, String password) implements Command<IdResponse> {}
