package com.ozan.service;

import com.ozan.dto.SchoolDTO;
import java.util.List;

public interface SchoolService {
    SchoolDTO save(SchoolDTO school);

    List<SchoolDTO> listAllSchools();

    SchoolDTO updateSchool(SchoolDTO schoolDTO);

    SchoolDTO findSchoolByName(String schoolName);

    void delete(String schoolName);
}
