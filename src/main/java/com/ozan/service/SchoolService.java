package com.ozan.service;

import com.ozan.dto.SchoolDTO;
import com.ozan.dto.VehicleDTO;

import java.util.List;

public interface SchoolService {
    SchoolDTO save(SchoolDTO school);

    List<SchoolDTO> listAllSchools();

    SchoolDTO updateSchool(SchoolDTO schoolDTO);

    SchoolDTO findSchoolByName(String schoolName);

    void delete(String schoolName);

    List<SchoolDTO> listAvailableSchools();
}
