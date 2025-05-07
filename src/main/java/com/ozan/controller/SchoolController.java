package com.ozan.controller;

import com.ozan.dto.ResponseWrapper;
import com.ozan.dto.SchoolDTO;
import com.ozan.service.SchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/school")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createSchool(@RequestBody SchoolDTO school) {
        SchoolDTO schoolDTO = schoolService.save(school);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "School is created."
                , 201, schoolDTO);
        return ResponseEntity.status(201).body(responseWrapper);
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllSchools() {
        List<SchoolDTO> schoolDTOList = schoolService.listAllSchools();
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Schools are retrieved"
                , HttpStatus.OK.value(), schoolDTOList);
        return ResponseEntity.status(200).body(responseWrapper);
    }

    @GetMapping("/{schoolName}")
    public ResponseEntity<ResponseWrapper> getSchoolByName(@PathVariable("schoolName") String schoolName) {
        SchoolDTO schoolDTO = schoolService.findSchoolByName(schoolName);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "School is retrieved"
                , HttpStatus.OK.value(), schoolDTO);
        return ResponseEntity.status(200).body(responseWrapper);
    }

    @PutMapping("/{schoolName}")
    public ResponseEntity<ResponseWrapper> updateSchool(@PathVariable("schoolName") String schoolName,
                                                        @RequestBody SchoolDTO schoolDTO) {
        SchoolDTO updatedSchoolDTO = schoolService.updateSchool(schoolDTO);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "School is updated."
                , 200, updatedSchoolDTO);
        return ResponseEntity.status(200).body(responseWrapper);
    }

    @DeleteMapping("/{schoolName}")
    public ResponseEntity<ResponseWrapper> deleteSchool(@PathVariable("schoolName") String schoolName) {
        schoolService.delete(schoolName);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "School is deleted."
                , HttpStatus.OK.value(), null);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);

    }

}
