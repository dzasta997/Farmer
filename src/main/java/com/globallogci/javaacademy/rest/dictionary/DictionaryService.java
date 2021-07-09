package com.globallogci.javaacademy.rest.dictionary;

import com.globallogci.javaacademy.rest.exception.DictionaryApiException;
import com.globallogci.javaacademy.rest.model.oxford.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class DictionaryService {

    private static final String APP_ID = "";
    private static final String APP_KEY = "";
    private static final String LANGUAGE = "en-gb";


    private final RestTemplate dictionaryRestTemplate;
    private final WebClient dictionaryWebClient;

    public DictionaryService(RestTemplate dictionaryRestTemplate, WebClient dictionaryWebClient) {
        this.dictionaryRestTemplate = dictionaryRestTemplate;
        this.dictionaryWebClient = dictionaryWebClient;
    }

    public String getInfo(final String word) {
        final String wordId = word.toLowerCase();
        final String restUrl = "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + LANGUAGE + "/" + wordId ;

        final HttpHeaders headers = createHeader();
        final HttpEntity<Object> objectHttpEntity = new HttpEntity<>(headers);

        final Map<Object, Object> params = Map.of(
                "strictMatch", false
        );
        final ResponseEntity<OxfordResponse> exchange = dictionaryRestTemplate.exchange(restUrl, HttpMethod.GET, objectHttpEntity, OxfordResponse.class, params);

        return Optional.of(exchange)
                .map(HttpEntity::getBody)
                .flatMap(OxfordResponse::getFirstSense)
                .orElse("");
    }

    public String getInfoWebClient(final String word) {
        final String wordId = word.toLowerCase();
        final OxfordResponse response = dictionaryWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v2/entries/" + LANGUAGE + "/" + wordId)
                        .queryParam("strictMatch", false)
                        .build()
                )
                .retrieve()
                .bodyToMono(OxfordResponse.class)
                .block();

        return Optional.ofNullable(response)
                .flatMap(OxfordResponse::getFirstSense)
                .orElse("");
    }


    private HttpHeaders createHeader() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.put("app_id", List.of(APP_ID));
        httpHeaders.put("app_key", List.of(APP_KEY));
        return httpHeaders;
    }
}
