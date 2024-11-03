package com.bastien_corre.cleanjava.wine_enthusiast.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.product.domain.viewmodel.IdResponse;

import java.util.List;

public record CreateWineEnthusiastCommand(String firstName, String lastName, List<String> preferredWines ) implements Command<IdResponse> {}
