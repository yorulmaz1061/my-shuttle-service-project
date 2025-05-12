package com.ozan.repository;

import com.ozan.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
    School findBySchoolNameAndIsDeleted(String schoolName, boolean isDeleted);

    List<School> findAllByIsDeletedOrderBySchoolName(boolean isDeleted);


}
