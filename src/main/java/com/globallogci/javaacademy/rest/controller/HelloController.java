package com.globallogci.javaacademy.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello/{name}")
    public String hello(@PathVariable String name, @RequestParam String surname) {
        return "Hello " + name + " " + surname;
    }

}