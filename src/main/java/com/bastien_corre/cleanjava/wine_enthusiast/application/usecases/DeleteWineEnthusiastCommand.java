package com.bastien_corre.cleanjava.wine_enthusiast.application.usecases;

import an.awesome.pipelinr.Command;

public record DeleteWineEnthusiastCommand(String id) implements Command<Void> {}
