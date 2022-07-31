package com.globallogic.rest.weather.model;

public class WeatherInfo {

    private MainWeather main;

    public MainWeather getMain() {
        return main;
    }

    public WeatherInfo setMain(MainWeather main) {
        this.main = main;
        return this;
    }
}
