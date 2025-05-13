package com.ozan.client;

import com.ozan.dto.weather.WeatherResponse;
import feign.Logger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://api.weatherstack.com", name = "WEATHER-CLIENT")
public interface WeatherApiClient {
    @GetMapping("/current")
    WeatherResponse getCurrentTemperature(@RequestParam(value = "access_key") String accessKey,
                                          @RequestParam(value = "query") String city);

}

