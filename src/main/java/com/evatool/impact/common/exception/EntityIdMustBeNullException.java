package com.evatool.impact.common.exception;

public class EntityIdMustBeNullException extends RuntimeException {
    public EntityIdMustBeNullException(String entity) {
        super(String.format("A newly created '%s' cannot have an id.", entity));
    }
}
