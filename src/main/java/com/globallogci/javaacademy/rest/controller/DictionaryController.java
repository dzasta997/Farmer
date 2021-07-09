package com.globallogci.javaacademy.rest.controller;

import com.globallogci.javaacademy.rest.dictionary.DictionaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictionaryController {

    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("words/{word}")
    public String getInfo(@PathVariable String word) {
        return dictionaryService.getInfo(word);
    }

    @GetMapping("words/webclient/{word}")
    public String getInfoWebClient(@PathVariable String word) {
        return dictionaryService.getInfoWebClient(word);
    }
}
