package com.bastien_corre.cleanjava.wine_enthusiast.application.usecases;

import an.awesome.pipelinr.Command;

public record UpdateWineEnthusiastInfosCommand(String id, String firstName, String lastName) implements Command<Void> {}
