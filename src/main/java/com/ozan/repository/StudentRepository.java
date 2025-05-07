package com.ozan.repository;

import com.ozan.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    List<Student> findAllByIsDeletedOrderByUserFirstName(boolean isDeleted);

    Student findByUserTcId(String userTcId);

    Student findByUserTcIdAndIsDeleted(String userTcId, Boolean isDeleted);

}
