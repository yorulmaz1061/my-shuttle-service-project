package com.ozan.controller;

import com.ozan.dto.*;
import com.ozan.service.SchoolService;
import com.ozan.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    private final SchoolService schoolService;

    public VehicleController(VehicleService vehicleService, SchoolService schoolService) {
        this.vehicleService = vehicleService;
        this.schoolService = schoolService;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createVehicle(@RequestBody VehicleDTO vehicle) {
        VehicleDTO vehicleDTO = vehicleService.save(vehicle);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Vehicle is created."
                , HttpStatus.CREATED.value(), vehicleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseWrapper);
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllVehicles() {
        List<VehicleDTO> vehicleDTOList = vehicleService.listAllVehicles();
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Vehicles are listed."
                , HttpStatus.OK.value(), vehicleDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }

    @GetMapping("/{vehiclePlateNumber}")
    public ResponseEntity<ResponseWrapper> getVehicleByPlateNumber(@PathVariable("vehiclePlateNumber") String plateNumber) {
        VehicleDTO vehicleDTO = vehicleService.findVehicleByPlateNumber(plateNumber);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Vehicle is retrieved"
                , HttpStatus.OK.value(), vehicleDTO);
        return ResponseEntity.status(200).body(responseWrapper);
    }

    @PutMapping("/{vehiclePlateNumber}")
    public ResponseEntity<ResponseWrapper> updateVehicle(@PathVariable("vehiclePlateNumber") String plateNumber, @RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO updatedVehicleDTO = vehicleService.updateVehicle(vehicleDTO);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Vehicle is updated."
                , HttpStatus.OK.value(), updatedVehicleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }

    @DeleteMapping("/{vehiclePlateNumber}")
    public ResponseEntity<ResponseWrapper> deleteVehicle(@PathVariable("vehiclePlateNumber") String plateNumber) {
        vehicleService.delete(plateNumber);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Vehicle is deleted."
                , HttpStatus.OK.value(), null);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);

    }

    @PostMapping("/{vehiclePlateNumber}/assign-driver")
    public ResponseEntity<ResponseWrapper> assignDriver(@PathVariable("vehiclePlateNumber") String plateNumber, @RequestBody DriverDTO driverDTO) {
        VehicleDTO driverAssignedVehicleDTO = vehicleService.assignToDriver(plateNumber, driverDTO.getUserTcId());
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Driver is assigned to vehicle.",
                HttpStatus.OK.value(), driverAssignedVehicleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }

    @PostMapping("/{vehiclePlateNumber}/assign-hostess")
    public ResponseEntity<ResponseWrapper> assignHostess(@PathVariable("vehiclePlateNumber") String plateNumber, @RequestBody HostessDTO hostessDTO) {
        VehicleDTO hostessAssignedVehicleDTO = vehicleService.assignToHostess(plateNumber, hostessDTO.getUserTcId());
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Hostess is assigned to vehicle.",
        HttpStatus.OK.value(), hostessAssignedVehicleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);

    }
    @PostMapping("/{vehiclePlateNumber}/assign-student")
    public ResponseEntity<ResponseWrapper> assignStudent(@PathVariable("vehiclePlateNumber") String plateNumber, @RequestBody StudentDTO studentDTO) {
        VehicleDTO studentAssignedVehicleDTO = vehicleService.assignToStudent(plateNumber,studentDTO.getUserTcId());
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Student is assigned to vehicle.",
                HttpStatus.OK.value(), studentAssignedVehicleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);

    }
    @PostMapping("/{vehiclePlateNumber}/assign-school")
    public ResponseEntity<ResponseWrapper> assignSchool(@PathVariable("vehiclePlateNumber") String plateNumber, @RequestBody SchoolDTO schoolDTO) {
        VehicleDTO schoolAssignedVehicleDTO = vehicleService.assignToSchool(plateNumber, schoolDTO.getId());
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "School is assigned to vehicle.",
                HttpStatus.OK.value(), schoolAssignedVehicleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }




}
