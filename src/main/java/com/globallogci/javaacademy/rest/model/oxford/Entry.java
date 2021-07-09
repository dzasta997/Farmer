package com.globallogci.javaacademy.rest.model.oxford;

import java.util.List;

public class Entry {
    private List<GrammaticalFeature> grammaticalFeatures;
    private String homographNumber;
    private List<Pronunciation> pronunciations;
    private List<Sens> senses;
    private List<String> etymologies;

    public List<GrammaticalFeature> getGrammaticalFeatures() {
        return grammaticalFeatures;
    }

    public void setGrammaticalFeatures(List<GrammaticalFeature> grammaticalFeatures) {
        this.grammaticalFeatures = grammaticalFeatures;
    }

    public String getHomographNumber() {
        return homographNumber;
    }

    public void setHomographNumber(String homographNumber) {
        this.homographNumber = homographNumber;
    }

    public List<Pronunciation> getPronunciations() {
        return pronunciations;
    }

    public void setPronunciations(List<Pronunciation> pronunciations) {
        this.pronunciations = pronunciations;
    }

    public List<Sens> getSenses() {
        return senses;
    }

    public void setSenses(List<Sens> senses) {
        this.senses = senses;
    }

    public List<String> getEtymologies() {
        return etymologies;
    }

    public void setEtymologies(List<String> etymologies) {
        this.etymologies = etymologies;
    }
}
