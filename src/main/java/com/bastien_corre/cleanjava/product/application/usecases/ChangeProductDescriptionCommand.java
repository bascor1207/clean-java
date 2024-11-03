package com.bastien_corre.cleanjava.product.application.usecases;

import an.awesome.pipelinr.Command;

public record ChangeProductDescriptionCommand(String id, String description) implements Command<Void> {}
