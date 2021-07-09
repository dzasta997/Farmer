package com.globallogci.javaacademy.rest.model.oxford;

import java.util.List;

public class Sens{
        private List<String> definitions;
        private String id;
        private List<SemanticClass> semanticClasses;
        private List<String> shortDefinitions;
        private List<DomainClass> domainClasses;
        private List<Register> registers;
        private List<Example> examples;
        private List<Region> regions;

        public List<String> getDefinitions() {
                return definitions;
        }

        public void setDefinitions(List<String> definitions) {
                this.definitions = definitions;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public List<SemanticClass> getSemanticClasses() {
                return semanticClasses;
        }

        public void setSemanticClasses(List<SemanticClass> semanticClasses) {
                this.semanticClasses = semanticClasses;
        }

        public List<String> getShortDefinitions() {
                return shortDefinitions;
        }

        public void setShortDefinitions(List<String> shortDefinitions) {
                this.shortDefinitions = shortDefinitions;
        }

        public List<DomainClass> getDomainClasses() {
                return domainClasses;
        }

        public void setDomainClasses(List<DomainClass> domainClasses) {
                this.domainClasses = domainClasses;
        }

        public List<Register> getRegisters() {
                return registers;
        }

        public void setRegisters(List<Register> registers) {
                this.registers = registers;
        }

        public List<Example> getExamples() {
                return examples;
        }

        public void setExamples(List<Example> examples) {
                this.examples = examples;
        }

        public List<Region> getRegions() {
                return regions;
        }

        public void setRegions(List<Region> regions) {
                this.regions = regions;
        }
}