package com.globallogci.javaacademy.rest.controller;

import com.globallogci.javaacademy.rest.dictionary.DictionaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("dictionaries/{word}")
    public ResponseEntity<String> sayHello(@PathVariable String word) {
        return ResponseEntity.ok("Hello " + dictionaryService.getDefinition(word));
    }

}
