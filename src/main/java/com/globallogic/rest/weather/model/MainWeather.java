package com.globallogic.rest.weather.model;

public class MainWeather {

    private Double temp;
    private Integer pressure;

    public Double getTemp() {
        return temp;
    }

    public MainWeather setTemp(Double temp) {
        this.temp = temp;
        return this;
    }

    public Integer getPressure() {
        return pressure;
    }

    public MainWeather setPressure(Integer pressure) {
        this.pressure = pressure;
        return this;
    }
}
