package com.ozan.service.impl;

import com.ozan.dto.DriverDTO;
import com.ozan.dto.VehicleDTO;
import com.ozan.entity.Driver;
import com.ozan.entity.Vehicle;
import com.ozan.enums.Status;
import com.ozan.exception.NotFoundException;
import com.ozan.mapper.MapperUtil;
import com.ozan.repository.DriverRepository;
import com.ozan.repository.VehicleRepository;
import com.ozan.service.DriverService;
import com.ozan.service.VehicleService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final MapperUtil mapperUtil;
    private final DriverService driverService;
    private final DriverRepository driverRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, MapperUtil mapperUtil, DriverService driverService, DriverRepository driverRepository) {
        this.vehicleRepository = vehicleRepository;
        this.mapperUtil = mapperUtil;
        this.driverService = driverService;
        this.driverRepository = driverRepository;
    }

    @Override
    public VehicleDTO save(VehicleDTO vehicleDTO) {
        vehicleDTO.setStatus(Status.ACTIVE);
        vehicleRepository.save(mapperUtil.convert(vehicleDTO, new Vehicle()));
        return mapperUtil.convert(vehicleDTO, new VehicleDTO());
    }

    @Override
    public List<VehicleDTO> listAllVehicles() {
        List<Vehicle> vehicleList = vehicleRepository.findAllByIsDeletedOrderByRouteNumberAsc(false);
        return vehicleList.stream().map(vehicle -> mapperUtil.convert(vehicle, new VehicleDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDTO findVehicleByPlateNumber(String plateNumber) {
        Vehicle vehicle = vehicleRepository.findByPlateNumberAndIsDeleted(plateNumber, false);
        if (vehicle == null) throw new NotFoundException("Vehicle not found.");
        return mapperUtil.convert(vehicle, new VehicleDTO());
    }

    @Override
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleRepository.findByPlateNumberAndIsDeleted(vehicleDTO.getPlateNumber(), false);
        Vehicle convertedVehicle = mapperUtil.convert(vehicleDTO, vehicle);
        convertedVehicle.setId(vehicle.getId());
        convertedVehicle.setStatus(Status.ACTIVE);
        vehicleRepository.save(convertedVehicle);
        return findVehicleByPlateNumber(vehicleDTO.getPlateNumber());
    }

    @Override
    public void delete(String plateNumber) {
        Vehicle vehicle = vehicleRepository.findByPlateNumberAndIsDeleted(plateNumber, false);
        vehicle.setIsDeleted(true);
        vehicleRepository.save(vehicle);
    }

    @Override
    public VehicleDTO assignToDriver(String plateNumber, String userTcId) {
        VehicleDTO vehicleDTO = findVehicleByPlateNumber(plateNumber);
        DriverDTO selectedDriverDTO = driverService.listAvailableDrivers()
                .stream().filter(driver -> driver.getUserTcId().equals(userTcId)).findFirst()
                .orElseThrow(() -> new NotFoundException("Driver not found or not available"));
        vehicleDTO.setDriverDTO(selectedDriverDTO);
        selectedDriverDTO.setVehicleDTO(vehicleDTO);
        Vehicle vehicle = mapperUtil.convert(vehicleDTO, new Vehicle());
        vehicleRepository.save(vehicle);
        Driver driver = mapperUtil.convert(selectedDriverDTO, new Driver());
        driverRepository.save(driver);
        return mapperUtil.convert(vehicle, new VehicleDTO());

    }


}