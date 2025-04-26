package com.ozan.repository;

import com.ozan.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findAllByIsDeletedOrderByUserFirstName(boolean isDeleted);

    Driver findByUserTcId(String userTcId);

    Driver findByUserTcIdAndIsDeleted(String userTcId, boolean isDeleted);
}
