package com.globallogic.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfig {

    @Bean
    public RestTemplate weatherRestTemplate(WeatherRestTemplateResponseErrorHandler weatherRestTemplateResponseErrorHandler) {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(weatherRestTemplateResponseErrorHandler);
        // .. add handlers
        return restTemplate;
    }
}
