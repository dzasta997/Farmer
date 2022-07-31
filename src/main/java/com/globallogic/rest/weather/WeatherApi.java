package com.globallogic.rest.weather;

import com.globallogic.rest.weather.model.WeatherInfo;

public interface WeatherApi {

    WeatherInfo getWeather(String city);


}
