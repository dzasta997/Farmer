package com.globallogic.rest.weather;

import com.globallogic.rest.weather.model.WeatherInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@ConditionalOnProperty(name = "javaacademy.weather.api.client", havingValue = "WEB_CLIENT")
public class WeatherWebClientApi implements WeatherApi {

    private final String prefixUrl;
    private final WebClient weatherWebClient;


    public WeatherWebClientApi(@Value("${javaacademy.weather.api.url.prefix}") final String prefixUrl,
                               final WebClient weatherWebClient) {
        this.prefixUrl = prefixUrl;
        this.weatherWebClient = weatherWebClient;
    }

    public WeatherInfo getWeather(final String city) {
        return weatherWebClient.get()
                .uri(uri -> uri.path(prefixUrl)
                        .queryParam("q", city)
                        .queryParam("apikey", "{apikey}")
                        .queryParam("units", "{units}")
                        .build())
                .retrieve()
                .bodyToMono(WeatherInfo.class)
                .block();
    }

}