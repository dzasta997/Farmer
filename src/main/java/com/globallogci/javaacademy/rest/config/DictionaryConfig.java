package com.globallogci.javaacademy.rest.config;

import com.globallogci.javaacademy.rest.dictionary.DictionaryRestTemplateResponseErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DictionaryConfig {

    @Bean
    public RestTemplate dictionaryRestTemplate() {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DictionaryRestTemplateResponseErrorHandler());
        return restTemplate;
    }

}
