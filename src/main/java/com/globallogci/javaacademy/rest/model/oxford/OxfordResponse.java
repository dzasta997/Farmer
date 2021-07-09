package com.globallogci.javaacademy.rest.model.oxford;

import java.util.List;
import java.util.Optional;

public class OxfordResponse {

    private String id;
    private Metadata metadata;
    private List<Result> results;
    private String word;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Optional<String> getFirstSense() {
        return results.stream().findFirst()
                .map(Result::getLexicalEntries)
                .flatMap(lexicalEntries -> lexicalEntries.stream().findFirst())
                .map(LexicalEntry::getEntries)
                .flatMap(entries -> entries.stream().findFirst())
                .map(Entry::getSenses)
                .flatMap(senses -> senses.stream().findFirst())
                .map(Sens::getDefinitions)
                .flatMap(definitions -> definitions.stream().findFirst());
    }

}
