package com.ozan.service;

import com.ozan.dto.StudentDTO;

public interface StudentService {
    StudentDTO save(StudentDTO student);
    StudentDTO findById(long id);

}
