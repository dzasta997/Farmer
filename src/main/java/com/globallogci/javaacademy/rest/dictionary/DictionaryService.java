package com.globallogci.javaacademy.rest.dictionary;

import com.globallogci.javaacademy.rest.model.oxford.OxfordResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Service
public class DictionaryService {

    private static final String APP_ID = "";
    private static final String APP_KEY = "";

    private static final String LANGUAGE = "en-gb";

    public String getInfo(final String word) {
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        final String restUrl = "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + LANGUAGE + "/" + word_id + "?" + "strictMatch=" + strictMatch;

        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = createHeader();
        final HttpEntity<Object> objectHttpEntity = new HttpEntity<>(headers);

        final Map<Object, Object> params = Map.of();
        final ResponseEntity<OxfordResponse> exchange = restTemplate.exchange(restUrl, HttpMethod.GET, objectHttpEntity, OxfordResponse.class, params);
        return exchange.getBody().getResults().get(0).getLexicalEntries().get(0).getEntries().get(0).getSenses().get(0).getDefinitions().get(0);
    }

    private HttpHeaders createHeader() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.put("app_id", List.of(APP_ID));
        httpHeaders.put("app_key", List.of(APP_KEY));
        return httpHeaders;
    }
}
