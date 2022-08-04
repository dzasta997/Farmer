package com.globallogic.rest.controller;

import com.globallogic.rest.server.HelloService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerUnitTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    HelloService helloService;

    @Test
    @DisplayName("Inline verification")
    void shouldReturnGreetings() throws Exception {
        when(helloService.getMessage("Justyna", false, false, null)).thenReturn("Hello Justyna");
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