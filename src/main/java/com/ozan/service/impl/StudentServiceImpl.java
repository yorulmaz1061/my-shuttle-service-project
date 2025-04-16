package com.ozan.service.impl;

import com.ozan.dto.StudentDTO;
import com.ozan.entity.Student;
import com.ozan.enums.Status;
import com.ozan.enums.UserType;
import com.ozan.exception.NotFoundException;
import com.ozan.mapper.MapperUtil;
import com.ozan.repository.StudentRepository;
import com.ozan.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final MapperUtil mapperUtil;

    public StudentServiceImpl(StudentRepository studentRepository, MapperUtil mapperUtil) {
        this.studentRepository = studentRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        studentDTO.setStatus(Status.ACTIVE);
        studentDTO.setUserType(UserType.STUDENT);
        Student student = mapperUtil.convert(studentDTO, new Student());
        studentRepository.save(student);
        return mapperUtil.convert(student,new StudentDTO());
    }

    @Override
    public StudentDTO findById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found"));
        return null;
    }

}
