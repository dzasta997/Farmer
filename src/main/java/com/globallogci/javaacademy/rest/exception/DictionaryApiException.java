package com.globallogci.javaacademy.rest.exception;

import org.springframework.http.HttpStatus;

public class DictionaryApiException extends RuntimeException {

    public DictionaryApiException(HttpStatus httpStatus, String body) {
        super(httpStatus.toString() + ": " + body);
    }

}
