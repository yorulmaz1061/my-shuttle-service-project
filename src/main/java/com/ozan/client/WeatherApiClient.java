package com.ozan.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://api.weatherstack.com", name = "WEATHER-CLIENT")
public interface WeatherApiClient {
    @GetMapping("/current")
    String getCurrentTemperature(@RequestParam(value = "access_key") String accessKey,
                                 @RequestParam(value = "query") String city);


}
