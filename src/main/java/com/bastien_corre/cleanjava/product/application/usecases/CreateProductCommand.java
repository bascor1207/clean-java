package com.bastien_corre.cleanjava.product.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.product.domain.viewmodel.IdResponse;

public record CreateProductCommand(String productName, String productDescription, Float productPrice) implements Command<IdResponse> {}
