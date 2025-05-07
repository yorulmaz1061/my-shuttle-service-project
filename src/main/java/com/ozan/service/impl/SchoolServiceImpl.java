package com.ozan.service.impl;

import com.ozan.dto.SchoolDTO;
import com.ozan.entity.School;
import com.ozan.exception.NotFoundException;
import com.ozan.mapper.MapperUtil;
import com.ozan.repository.SchoolRepository;
import com.ozan.service.SchoolService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {
    SchoolRepository schoolRepository;
    MapperUtil mapperUtil;
    public SchoolServiceImpl(SchoolRepository schoolRepository, MapperUtil mapperUtil) {
        this.schoolRepository = schoolRepository;
        this.mapperUtil = mapperUtil;
    }
    @Override
    public SchoolDTO save(SchoolDTO school) {
        schoolRepository.save(mapperUtil.convert(school, new School()));
        return mapperUtil.convert(school, new SchoolDTO());
    }

    @Override
    public List<SchoolDTO> listAllSchools() {
        List<School> schoolList = schoolRepository.findAll();
        return schoolList.stream().map(school -> mapperUtil.convert(school, new SchoolDTO()))
                .collect(Collectors.toList());
    }
    @Override
    public SchoolDTO findSchoolByName(String schoolName) {
        School school = schoolRepository.findBySchoolNameAndIsDeleted(schoolName, false);
        if(school != null) return mapperUtil.convert(school, new SchoolDTO());
        else throw new NotFoundException("School not found.");

    }

    @Override
    public void delete(String schoolName) {
        School school = schoolRepository.findBySchoolNameAndIsDeleted(schoolName,false);
        school.setIsDeleted(true);
        schoolRepository.save(school);
    }

    @Override
    public SchoolDTO updateSchool(SchoolDTO schoolDTO) {
        School school = schoolRepository.findBySchoolNameAndIsDeleted(schoolDTO.getSchoolName(), false);
        School convertedSchool = mapperUtil.convert(schoolDTO,school);
        convertedSchool.setId(school.getId());
        schoolRepository.save(convertedSchool);
        return findSchoolByName(schoolDTO.getSchoolName());
    }



}
