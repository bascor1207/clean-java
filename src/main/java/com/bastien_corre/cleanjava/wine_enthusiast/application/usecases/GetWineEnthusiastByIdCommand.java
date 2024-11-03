package com.bastien_corre.cleanjava.wine_enthusiast.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.wine_enthusiast.domain.viewmodel.WineEnthusiastViewModel;

public record GetWineEnthusiastByIdCommand(String id) implements Command<WineEnthusiastViewModel> {}
