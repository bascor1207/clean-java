package com.bastien_corre.cleanjava.core.domain.exceptions;

public class IllegalArgumentException extends RuntimeException {
    public IllegalArgumentException(String entity, String key, String value, String valueType) {
        super(
                String.format("%s with key %s and value %s of type %s is not valid",
                        entity,
                        key,
                        value,
                        valueType)
        );
    }

    public IllegalArgumentException(String simpleMessage) {
        super(simpleMessage);
    }
}
