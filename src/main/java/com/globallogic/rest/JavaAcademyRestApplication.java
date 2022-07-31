package com.globallogic.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class JavaAcademyRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaAcademyRestApplication.class, args);
    }

}
