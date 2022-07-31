package com.globallogic.rest.exception;

import org.springframework.http.HttpStatus;

public class WeatherApiException extends ApplicationException {

    public WeatherApiException(HttpStatus httpStatus, String body) {
        super("Cannot connect to the Weather API - " + httpStatus.toString() + ": " + body);
    }
}
