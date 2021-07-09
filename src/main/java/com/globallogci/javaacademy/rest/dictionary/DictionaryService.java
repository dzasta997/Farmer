package com.globallogci.javaacademy.rest.dictionary;

import com.globallogci.javaacademy.rest.model.oxford.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class DictionaryService {

    private static final String APP_ID = "";
    private static final String APP_KEY = "";
    private static final String LANGUAGE = "en-gb";


    private final RestTemplate dictionaryRestTemplate;

    public DictionaryService(RestTemplate dictionaryRestTemplate) {
        this.dictionaryRestTemplate = dictionaryRestTemplate;
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

    private HttpHeaders createHeader() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.put("app_id", List.of(APP_ID));
        httpHeaders.put("app_key", List.of(APP_KEY));
        return httpHeaders;
    }
}
