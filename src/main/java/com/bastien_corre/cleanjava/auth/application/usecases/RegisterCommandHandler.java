package com.bastien_corre.cleanjava.auth.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.product.domain.viewmodel.IdResponse;

public class RegisterCommandHandler implements Command.Handler<RegisterCommand, IdResponse> {

    @Override
    public IdResponse handle(RegisterCommand registerCommand) {
       return null;
    }
}
