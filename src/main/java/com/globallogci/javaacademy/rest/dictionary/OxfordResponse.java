package com.globallogci.javaacademy.rest.dictionary;

import java.util.List;
import java.util.Map;

public class OxfordResponse {

    private String id;
    private Map<String, Object> metadata;
    private List<Result> results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
