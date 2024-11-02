package com.bastien_corre.cleanjava.product.application.usecases;

import an.awesome.pipelinr.Command;

public record DeleteProductCommand(String id) implements Command<Void> {}
