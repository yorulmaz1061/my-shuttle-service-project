package com.ozan.repository;

import com.ozan.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByIsDeletedOrderByRouteNumberAsc(boolean isDeleted);

    Vehicle findByPlateNumberAndIsDeleted(String plateNumber, boolean isDeleted);

    Vehicle findByPlateNumber(String plateNumber);
}
