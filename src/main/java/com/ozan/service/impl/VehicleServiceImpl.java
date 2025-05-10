package com.ozan.service.impl;

import com.ozan.dto.DriverDTO;
import com.ozan.dto.HostessDTO;
import com.ozan.dto.StudentDTO;
import com.ozan.dto.VehicleDTO;
import com.ozan.entity.Driver;
import com.ozan.entity.Hostess;
import com.ozan.entity.Student;
import com.ozan.entity.Vehicle;
import com.ozan.enums.Status;
import com.ozan.exception.NotFoundException;
import com.ozan.exception.VehicleCapacityExceededException;
import com.ozan.mapper.MapperUtil;
import com.ozan.repository.DriverRepository;
import com.ozan.repository.VehicleRepository;
import com.ozan.service.DriverService;
import com.ozan.service.HostessService;
import com.ozan.service.StudentService;
import com.ozan.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final MapperUtil mapperUtil;
    private final DriverService driverService;
    private final HostessService hostessService;
    private final StudentService studentService;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, MapperUtil mapperUtil, DriverService driverService, DriverRepository driverRepository, HostessService hostessService, StudentService studentService) {
        this.vehicleRepository = vehicleRepository;
        this.mapperUtil = mapperUtil;
        this.driverService = driverService;
        this.hostessService = hostessService;
        this.studentService = studentService;
    }

    @Override
    public VehicleDTO save(VehicleDTO vehicleDTO) {
        vehicleDTO.setStatus(Status.ACTIVE);
        vehicleDTO.setTotalStudentCount(0);
        vehicleDTO.setIsFull(false);
        Vehicle savedVehicle = vehicleRepository.save(mapperUtil.convert(vehicleDTO, new Vehicle()));
        return mapperUtil.convert(savedVehicle, new VehicleDTO());
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
        Vehicle vehicle = vehicleRepository.findByPlateNumber(plateNumber);
        DriverDTO selectedDriverDTO = driverService.listAvailableDrivers()
                .stream().filter(driver -> driver.getUserTcId().equals(userTcId)).findFirst()
                .orElseThrow(() -> new NotFoundException("Driver not found or not available."));
        vehicle.setDriver(mapperUtil.convert(selectedDriverDTO, new Driver()));
        vehicle = vehicleRepository.save(vehicle);
        return mapperUtil.convert(vehicle, new VehicleDTO());

    }

    @Override
    public VehicleDTO assignToHostess(String plateNumber, String userTcId) {
        Vehicle vehicle = vehicleRepository.findByPlateNumber(plateNumber);
        HostessDTO selectedHostessDTO = hostessService.listAvailableHostess()
                .stream()
                .filter(hostess -> hostess.getUserTcId().equals(userTcId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Hostess not found or not available"));
        vehicle.setHostess(mapperUtil.convert(selectedHostessDTO, new Hostess()));
        vehicle = vehicleRepository.save(vehicle);
        return mapperUtil.convert(vehicle, new VehicleDTO());
    }

    @Override
    public VehicleDTO assignToStudent(String plateNumber, String userTcId) {
        Vehicle vehicle = vehicleRepository.findByPlateNumber(plateNumber);
        if (vehicle == null) throw new NotFoundException("Vehicle not found with plate number: " + plateNumber);
        if (vehicle.getStudentList().size() >= 2) {
            throw new VehicleCapacityExceededException("Cannot assign more than 2 students to a vehicle. Vehicle plate number: " + plateNumber);
        }

        StudentDTO selectedStudentDTO = studentService.listAvailableStudents()
                .stream().filter(student -> student.getUserTcId().equals(userTcId))
                .findFirst().orElseThrow(() -> new NotFoundException("Student not found or not available."));

        Student student = mapperUtil.convert(selectedStudentDTO, new Student());
        student.setVehicle(vehicle);
        vehicle.getStudentList().add(student);
        vehicle.setTotalStudentCount(vehicle.getTotalStudentCount() + 1);
        vehicle.setIsFull(vehicle.getTotalStudentCount() >= 2);
        vehicleRepository.save(vehicle);
        return mapperUtil.convert(vehicle, new VehicleDTO());

    }


}