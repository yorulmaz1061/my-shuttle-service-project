package com.ozan.service;


import com.ozan.dto.StudentDTO;
import java.util.List;

public interface StudentService {
    StudentDTO save(StudentDTO studentDTO);

    List<StudentDTO> listAllStudents();

    StudentDTO findByUserTcId(String userTcId);

    StudentDTO updateStudent(StudentDTO studentDTO);

    void delete(String userTcId);
}
