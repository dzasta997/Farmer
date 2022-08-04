package com.globallogic.rest;

import com.globallogic.rest.controller.HelloController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JavaAcademyRestApplicationTest {

    @Autowired
    private HelloController helloController;

    @Test
    void contextLoadsTest(){
        assertThat(helloController).isNotNull();
    }

}