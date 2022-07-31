package com.globallogic.rest.exception.handler;

public class ErrorResponse {

    private final String message;

    public ErrorResponse(Throwable throwable) {
        this.message = throwable.getMessage();
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
