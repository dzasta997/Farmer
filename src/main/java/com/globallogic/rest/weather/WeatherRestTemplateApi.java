package com.globallogic.rest.weather;

import com.globallogic.rest.weather.model.WeatherInfo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.slf4j.LoggerFactory.getLogger;

@Component
@ConditionalOnProperty(name = "javaacademy.weather.api.client", havingValue = "REST_TEMPLATE")
public class WeatherRestTemplateApi implements WeatherApi {

    private static final Logger logger = getLogger(WeatherRestTemplateApi.class);

    private final String url;
    private final String apiKey;
    private final RestTemplate weatherRestTemplate;


    public WeatherRestTemplateApi(@Value("${javaacademy.weather.api.apiKey}") final String apiKey,
                                  @Value("${javaacademy.weather.api.url.base}") final String baseUrl,
                                  @Value("${javaacademy.weather.api.url.prefix}") final String prefixUrl,
                                  final RestTemplate weatherRestTemplate) {
        this.url = baseUrl + prefixUrl;
        this.apiKey = apiKey;
        this.weatherRestTemplate = weatherRestTemplate;
    }

    public WeatherInfo getWeather(final String city) {
        final String requestUrl = url + "?q=" + city + "&appid=" + apiKey + "&units=metric";
//        final String requestUrl = url + "?q={city}&appid={appid}&units={units}";;
        logger.info("Sending request to {}", requestUrl);
//        final ResponseEntity<WeatherInfo> responseEntity = restTemplate.exchange(requestUrl, HttpMethod.GET, null, WeatherInfo.class, city, apiKey, "metric");
        final ResponseEntity<WeatherInfo> responseEntity = weatherRestTemplate.getForEntity(requestUrl, WeatherInfo.class);
        return responseEntity.getBody();
    }

}
