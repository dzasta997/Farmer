package com.globallogci.javaacademy.rest.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id, Class<?> entityClass) {
        super("Entity " + entityClass.getSimpleName() + " with id " + id + " was not found");
    }

}
