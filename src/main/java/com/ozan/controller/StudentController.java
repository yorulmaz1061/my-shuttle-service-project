package com.ozan.controller;
import com.ozan.dto.ResponseWrapper;
import com.ozan.dto.StudentDTO;
import com.ozan.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<ResponseWrapper>getAllStudents(){
        List<StudentDTO> studentDTOList = studentService.listAllStudents();
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Students are listed."
                , HttpStatus.OK.value(), studentDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }
    @GetMapping("/{userTcId}")
    public ResponseEntity<ResponseWrapper>getStudentByTcId(@PathVariable("userTcId") String userTcId){
        StudentDTO studentDTO = studentService.findByUserTcId(userTcId);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Student is listed."
                , HttpStatus.OK.value(), studentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }
    @PutMapping("/{userTcId}")
    public ResponseEntity<ResponseWrapper>updateStudent(@PathVariable("userTcId") String userTcId, @RequestBody StudentDTO studentDTO){
        StudentDTO updatedStudentDTO = studentService.updateStudent(studentDTO);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Student is updated."
                , HttpStatus.OK.value(), updatedStudentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }
    @DeleteMapping("/{userTcId}")
    public ResponseEntity<ResponseWrapper>deleteStudent(@PathVariable("userTcId") String userTcId){
        studentService.delete(userTcId);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Student is deleted."
                , HttpStatus.OK.value(), null);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);

    }

}