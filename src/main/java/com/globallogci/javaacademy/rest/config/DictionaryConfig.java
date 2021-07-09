package com.globallogci.javaacademy.rest.config;

import com.globallogci.javaacademy.rest.dictionary.DictionaryRestTemplateResponseErrorHandler;
import com.globallogci.javaacademy.rest.exception.DictionaryApiException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class DictionaryConfig {

    private static final String APP_ID = "https://developer.oxforddictionaries.com/";
    private static final String APP_KEY = "https://developer.oxforddictionaries.com/";

    @Bean
    public RestTemplate dictionaryRestTemplate() {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DictionaryRestTemplateResponseErrorHandler());
        return restTemplate;
    }

    @Bean
    public WebClient dictionaryWebClient() {
        return WebClient.builder()
                .baseUrl("https://od-api.oxforddictionaries.com:443")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("app_id", APP_ID)
                .defaultHeader("app_key", APP_KEY)
                .filter(errorHandlingFilter())
                .build();
    }

    private ExchangeFilterFunction errorHandlingFilter() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().isError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new DictionaryApiException(clientResponse.statusCode(), errorBody)));
            } else {
                return Mono.just(clientResponse);
            }
        });
    }

}
