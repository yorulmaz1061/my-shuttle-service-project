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

import java.util.List;
import java.util.stream.Collectors;

@Service
public  class StudentServiceImpl implements StudentService {
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
        Student savedStudent = studentRepository.save(mapperUtil.convert(studentDTO, new Student()));
        return mapperUtil.convert(savedStudent, new StudentDTO());


    }

    @Override
    public List<StudentDTO> listAllStudents() {
        List<Student> studentList = studentRepository.findAllByIsDeletedOrderByUserFirstName(false);
        return studentList.stream().map(student -> mapperUtil.convert(student,new StudentDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO findByUserTcId(String userTcId) {
        Student student = studentRepository.findByUserTcId(userTcId);
        if(student == null) throw new NotFoundException("Student not found.");
        return mapperUtil.convert(student,new StudentDTO());
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        Student student = studentRepository.findByUserTcIdAndIsDeleted(studentDTO.getUserTcId(),false);
        Student convertedStudent = mapperUtil.convert(studentDTO,student);
        convertedStudent.setId(student.getId());
        convertedStudent.setStatus(Status.ACTIVE);
        studentRepository.save(convertedStudent);
        return findByUserTcId(studentDTO.getUserTcId());

    }

    @Override
    public void delete(String userTcId) {
        Student student = studentRepository.findByUserTcIdAndIsDeleted(userTcId,false);
        student.setIsDeleted(true);
        student.setStatus(Status.PASSIVE);
        studentRepository.save(student);
    }


}
