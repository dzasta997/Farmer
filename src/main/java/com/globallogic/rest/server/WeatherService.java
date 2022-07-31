package com.globallogic.rest.server;

import com.globallogic.rest.model.Farmer;
import com.globallogic.rest.weather.WeatherApi;
import com.globallogic.rest.weather.model.MainWeather;
import com.globallogic.rest.weather.model.WeatherInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class WeatherService {

    private final FarmerService farmerService;
    private final WeatherApi weatherApi;

    public WeatherService(FarmerService farmerService, WeatherApi weatherApi) {
        this.farmerService = farmerService;
        this.weatherApi = weatherApi;
    }

    @GetMapping("/farmers/{framerId}")
    public MainWeather getWeatherByFarmerId(@PathVariable Long framerId) {
        final Farmer farmer = farmerService.getFarmerById(framerId);
        final String city = farmer.getCity();
        final WeatherInfo weather = weatherApi.getWeather(city);
        return weather.getMain();
    }
}
