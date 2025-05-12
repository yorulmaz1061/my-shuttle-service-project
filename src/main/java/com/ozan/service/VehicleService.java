package com.ozan.service;

import com.ozan.dto.VehicleDTO;
import java.util.List;

public interface VehicleService {
    VehicleDTO save(VehicleDTO vehicle);

    List<VehicleDTO> listAllVehicles();

    VehicleDTO findVehicleByPlateNumber(String plateNumber);

    VehicleDTO updateVehicle(VehicleDTO vehicleDTO);

    void delete(String plateNumber);

    VehicleDTO assignToDriver(String plateNumber, String userTcId);

    VehicleDTO assignToHostess(String plateNumber, String userTcId);

    VehicleDTO assignToStudent(String plateNumber, String userTcId);

    VehicleDTO assignToSchool(String plateNumber, Long id);
}
