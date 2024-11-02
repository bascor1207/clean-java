package com.bastien_corre.cleanjava.product.application.usecases;

import an.awesome.pipelinr.Command;
import com.bastien_corre.cleanjava.product.domain.viewmodel.ProductViewModel;

public record GetProductByIdCommand(String id) implements Command<ProductViewModel> {}