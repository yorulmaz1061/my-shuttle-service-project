package com.ozan.service;

import com.ozan.dto.DriverDTO;
import java.util.List;

public interface DriverService {

    DriverDTO save(DriverDTO driver);

    List<DriverDTO> listAllDrivers();

    DriverDTO findByUserTcId(String userTcId);

    DriverDTO updateDriver(DriverDTO driverDTO);

    void delete(String userTcId);

    List<DriverDTO> listAvailableDrivers();


}
