package com.globallogci.javaacademy.rest.exception;

public class EntityToUpdateHasNoIdException extends RuntimeException {

    public EntityToUpdateHasNoIdException(Class<?> entityClass) {
        super("Entity " + entityClass.getSimpleName() + " to update has no id");
    }

}
