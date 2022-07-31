package com.globallogic.rest.config;

import com.globallogic.rest.exception.WeatherApiException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class WebClientConfig {

    private static final Logger logger = getLogger(WebClientConfig.class);

    @Bean
    public WebClient weatherWebClient(@Value("${javaacademy.weather.api.apiKey}") final String apiKey,
                                      @Value("${javaacademy.weather.api.url.base}") final String baseUrl) {
        final Map<String, String> uriVariables = Map.of(
                "appid", apiKey,
                "units", "metric"
        );
        return WebClient.builder()
                .baseUrl(baseUrl)
                .filter(logRequest())
                .filter(errorHandlingFilter())
                .defaultUriVariables(uriVariables)
                .build();
    }

    private ExchangeFilterFunction errorHandlingFilter() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().isError()) {
                return clientResponse
                        .bodyToMono(String.class)
                        .flatMap(errorBody -> Mono.error(new WeatherApiException(clientResponse.statusCode(), errorBody)));
            } else {
                return Mono.just(clientResponse);
            }
        });
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            logger.info("Request - url: {}, body: {}", request.url(), request.body() );
            return Mono.just(request);
        });
    }
}
