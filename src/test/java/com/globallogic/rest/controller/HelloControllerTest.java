package com.globallogic.rest.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Inline verification")
    void shouldReturnGreetings() throws Exception {
        mockMvc.perform(get("/hello/Justyna"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Justyna"));
    }
    @Test
    @DisplayName("Exporting into variable and asserting response")
    void shouldReturnGreetings_ExportIntoVariable() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/hello/Justyna"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Justyna"))
                .andReturn();
        assertEquals("Hello Justyna", mvcResult.getResponse().getContentAsString());
    }






}