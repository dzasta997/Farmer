package com.globallogci.javaacademy.rest.dictionary;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class DictionaryService {

    private static final String LANGUAGE = "en-gb";
    private static final String STRICT_MATCH = "false";
    private static final String APP_ID = "";
    private static final String APP_KEY = "";

    public String getDefinition(String word) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders httpHeaders = createHeader();
        final String word_id = word.toLowerCase();
        final String restUrl = "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + LANGUAGE + "/" + word_id;

        final HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
        final Map<Object, Object> params = Map.of(
                "strictMatch", STRICT_MATCH
        );
        final String body = restTemplate.exchange(restUrl, HttpMethod.GET, entity, String.class, params).getBody();
//        final OxfordResponse body = restTemplate.exchange(restUrl, HttpMethod.GET, entity, OxfordResponse.class, params).getBody();
        return body;
    }

    private HttpHeaders createHeader() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.put("app_id", List.of(APP_ID));
        httpHeaders.put("app_key", List.of(APP_KEY));
        return httpHeaders;
    }
}
