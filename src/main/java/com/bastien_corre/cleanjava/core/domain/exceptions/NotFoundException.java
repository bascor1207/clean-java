package com.bastien_corre.cleanjava.core.domain.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entity, String key) {
        super(
                String.format("%s with key %s was not found",
                        entity,
                        key)
        );
    }
}
