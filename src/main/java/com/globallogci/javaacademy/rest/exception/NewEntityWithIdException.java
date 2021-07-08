package com.globallogci.javaacademy.rest.exception;

public class NewEntityWithIdException extends RuntimeException {

    public NewEntityWithIdException(Long id, Class<?> entityClass) {
        super("A new " + entityClass.getSimpleName() + " cannot already have an ID: " + id);
    }

}
