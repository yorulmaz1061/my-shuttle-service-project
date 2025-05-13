package com.ozan.service.impl;

import com.ozan.client.WeatherApiClient;
import com.ozan.dto.SchoolDTO;
import com.ozan.dto.weather.WeatherResponse;
import com.ozan.entity.School;
import com.ozan.exception.NotFoundException;
import com.ozan.mapper.MapperUtil;
import com.ozan.repository.SchoolRepository;
import com.ozan.service.SchoolService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Value("${access_key}")
    private String accessKey;
    private final SchoolRepository schoolRepository;
    private final MapperUtil mapperUtil;
    private final WeatherApiClient weatherApiClient;


    public SchoolServiceImpl(SchoolRepository schoolRepository, MapperUtil mapperUtil, WeatherApiClient weatherApiClient) {
        this.schoolRepository = schoolRepository;
        this.mapperUtil = mapperUtil;
        this.weatherApiClient = weatherApiClient;

    }

    @Override
    public SchoolDTO save(SchoolDTO school) {
        schoolRepository.save(mapperUtil.convert(school, new School()));
        return mapperUtil.convert(school, new SchoolDTO());
    }

    @Override
    public List<SchoolDTO> listAllSchools() {
        List<School> schoolList = schoolRepository.findAll();
        return schoolList.stream().map(school -> {
            SchoolDTO dto = mapperUtil.convert(school, new SchoolDTO());

            try {
                Thread.sleep(1100);
                String decodedCity = URLDecoder.decode(dto.getSchoolCity(), StandardCharsets.UTF_8);
                Integer temperature = retrieveTemperatureByCity(decodedCity);
                dto.setCurrentTemperature(temperature);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                // Silently handle other exceptions and continue
            }

            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public SchoolDTO findSchoolByName(String schoolName) {
        School school = schoolRepository.findBySchoolNameAndIsDeleted(schoolName, false);
        if (school != null) return mapperUtil.convert(school, new SchoolDTO());
        else throw new NotFoundException("School not found.");

    }

    @Override
    public void delete(String schoolName) {
        School school = schoolRepository.findBySchoolNameAndIsDeleted(schoolName, false);
        school.setIsDeleted(true);
        schoolRepository.save(school);
    }

    @Override
    public List<SchoolDTO> listAvailableSchools() {
        List<School> schoolList = schoolRepository.findAllByIsDeletedOrderBySchoolName(false);
        return schoolList.stream().map(school -> mapperUtil.convert(school, new SchoolDTO())).collect(Collectors.toList());
    }

    @Override
    public SchoolDTO updateSchool(SchoolDTO schoolDTO) {
        School school = schoolRepository.findBySchoolNameAndIsDeleted(schoolDTO.getSchoolName(), false);
        School convertedSchool = mapperUtil.convert(schoolDTO, school);
        convertedSchool.setId(school.getId());
        schoolRepository.save(convertedSchool);
        return findSchoolByName(schoolDTO.getSchoolName());
    }

    private Integer retrieveTemperatureByCity(String city) {
        int maxRetries = 3;
        int retryCount = 0;
        long waitTime = 2000;
        String formattedCity = city.trim();

        while (retryCount < maxRetries) {
            try {
                WeatherResponse currentWeather = weatherApiClient.getCurrentTemperature(accessKey, formattedCity);

                if (currentWeather == null || currentWeather.getCurrent() == null) {
                    return null;
                }
                return currentWeather.getCurrent().getTemperature();
            } catch (Exception e) {
                retryCount++;
                if (retryCount < maxRetries) {
                    try {
                        Thread.sleep(waitTime);
                        waitTime *= 2;
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        return null;
                    }
                }
            }
        }
        return null;
    }


}
