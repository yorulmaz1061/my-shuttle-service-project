package com.ozan.service.impl;

import com.ozan.dto.DriverDTO;
import com.ozan.entity.Driver;
import com.ozan.enums.Status;
import com.ozan.enums.UserType;
import com.ozan.mapper.MapperUtil;
import com.ozan.repository.DriverRepository;
import com.ozan.service.DriverService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final MapperUtil mapperUtil;

    public DriverServiceImpl(DriverRepository driverRepository, MapperUtil mapperUtil) {
        this.driverRepository = driverRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public DriverDTO save(DriverDTO driver) {
        driver.setStatus(Status.ACTIVE);
        driver.setUserType(UserType.DRIVER);
        Driver savedDriver = driverRepository.save(mapperUtil.convert(driver, new Driver()));
        return mapperUtil.convert(savedDriver, new DriverDTO());

    }

    @Override
    public List<DriverDTO> listAllDrivers() {
        List<Driver> driverList = driverRepository.findAllByIsDeletedOrderByUserFirstName(false);
        return driverList.stream().map(driver -> mapperUtil.convert(driver,new DriverDTO()))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public DriverDTO findByUserTcId(String userTcId) {
        Driver driver = driverRepository.findByUserTcId(userTcId);
        if(driver == null) throw new IllegalArgumentException("Driver not found.");
        return mapperUtil.convert(driver,new DriverDTO());
    }

    @Override
    public DriverDTO updateDriver(DriverDTO driverDTO) {
        Driver driver = driverRepository.findByUserTcIdAndIsDeleted(driverDTO.getUserTcId(), false);
        Driver convertedDriver = mapperUtil.convert(driverDTO,driver);
        convertedDriver.setId(driver.getId());
        convertedDriver.setStatus(Status.ACTIVE);
        driverRepository.save(convertedDriver);
        return findByUserTcId(driverDTO.getUserTcId());
    }

    @Override
    public void delete(String userTcId) {
        Driver driver = driverRepository.findByUserTcIdAndIsDeleted(userTcId,false);
        driver.setIsDeleted(true);
        driver.setStatus(Status.PASSIVE);
        driverRepository.save(driver);
    }


    @Override
    public List<DriverDTO> listAvailableDrivers() {
        List<Driver> availableDrivers = driverRepository.findAllByVehicleIsNullAndIsDeletedFalseAndStatusEquals(Status.ACTIVE);
        return availableDrivers.stream().map(driver -> mapperUtil.convert(driver,new DriverDTO()))
                .collect(java.util.stream.Collectors.toList());


    }

}
