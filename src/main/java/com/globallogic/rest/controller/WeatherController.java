package com.globallogic.rest.controller;

import com.globallogic.rest.dto.FarmerDto;
import com.globallogic.rest.model.Farmer;
import com.globallogic.rest.server.WeatherService;
import com.globallogic.rest.weather.model.MainWeather;
import com.globallogic.rest.weather.model.WeatherInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("weathers")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/farmers/{framerId}")
    public ResponseEntity<MainWeather> getWeatherByFarmerId(@PathVariable Long framerId) {
        final MainWeather weather = weatherService.getWeatherByFarmerId(framerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(weather);
    }



}
