package com.evatool.impact.common.exception;

public class EntityNullException extends IllegalArgumentException {
    public EntityNullException(Class c) {
        super(String.format("%s must not be null.", c.getSimpleName()));
    }
}
