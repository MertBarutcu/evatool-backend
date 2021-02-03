package com.evatool.impact.common.exception;

public class IdNullException extends Exception {
    public IdNullException(Class c) {
        super(String.format("Illegal attempt to retrieve '%s' with null id", c.getSimpleName()));
    }
}
