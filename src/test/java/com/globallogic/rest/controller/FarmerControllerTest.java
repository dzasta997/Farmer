package com.globallogic.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.rest.dto.FarmerDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.regex.Matcher;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FarmerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateFarmerWhenValidRequestBody() throws Exception {
        String farmerName = "name";
        String cityName = "city";
        FarmerDto dto = new FarmerDto()
                .setCity(cityName)
                .setName(farmerName)
                .setStatus("status");
        mockMvc.perform(post("/farmers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content( new ObjectMapper().writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", Matchers.is(farmerName)))
                .andExpect(jsonPath("$.city", Matchers.is(cityName)));
    }

    @Test
    void shouldNotCreateFarmerWhenCityIsNull() throws Exception {
        String farmerName = "name";
        FarmerDto dto = new FarmerDto()
                .setName(farmerName)
                .setStatus("status");
        mockMvc.perform(post("/farmers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void shouldReturnAllFarmers() throws Exception {
        String farmerName = "name";
        String cityName = "city";
        FarmerDto dto = new FarmerDto()
                .setCity(cityName)
                .setName(farmerName)
                .setStatus("status");
        mockMvc.perform(post("/farmers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name", Matchers.is(farmerName)))
                .andExpect(jsonPath("$.city", Matchers.is(cityName)));
        dto = new FarmerDto().setCity("city2").setName("name2").setStatus("status");
        mockMvc.perform(post("/farmers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()));

        mockMvc.perform(get("/farmers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]id", notNullValue()));
    }

    @Test
    void shouldUpdateFarmer() throws Exception {
        String farmerName = "name";
        String cityName = "city";
        FarmerDto dto = new FarmerDto()
                .setCity(cityName)
                .setName(farmerName)
                .setStatus("status");
        MvcResult result = mockMvc.perform(post("/farmers")
                .contentType(MediaType.APPLICATION_JSON)
                .content( new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        FarmerDto created = mapper.readValue(content, FarmerDto.class);
        created.setCity("anotherCity");
        mockMvc.perform(put("/farmers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(created)))
                .andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get("/farmers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]city",Matchers.is("anotherCity")));

    }

    @Test
    void shouldGetFarmerById() throws Exception {
        String farmerName = "name";
        String cityName = "city";
        FarmerDto dto = new FarmerDto()
                .setCity(cityName)
                .setName(farmerName)
                .setStatus("status");
        mockMvc.perform(post("/farmers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andDo(print());
        mockMvc.perform(get("/farmers/1"))
                .andExpect(status().isOk());
    }

}