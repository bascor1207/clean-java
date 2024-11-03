package com.bastien_corre.cleanjava.wine_enthusiast.application.usecases;

import an.awesome.pipelinr.Command;

import java.util.List;

public record UpdateWineEnthusiastWineRelevantInfosCommand(String id, List<String> preferredWines) implements Command<Void> {}
