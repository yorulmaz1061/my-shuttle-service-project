package com.ozan.repository;

import com.ozan.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
    List<Salary> findByDriverUserTcId(String driverUserTcId);

    List<Salary> findSalaryByDriverUserTcId(String driverUserTcId);
}
