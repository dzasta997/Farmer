package com.globallogic.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UpdatingEntityWithoutIdException extends ApplicationClientException {

    public UpdatingEntityWithoutIdException(Class<?> classToUpdate) {
        super("Updating " + classToUpdate.getSimpleName() + " without Id");
    }
}
