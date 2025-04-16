package com.ozan.controller;

import com.ozan.dto.ResponseWrapper;
import com.ozan.dto.StudentDTO;
import com.ozan.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createStudent(@RequestBody StudentDTO student) {
        StudentDTO studentDTO = studentService.save(student);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Student is created."
                , HttpStatus.CREATED.value(), studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseWrapper);
    }
}