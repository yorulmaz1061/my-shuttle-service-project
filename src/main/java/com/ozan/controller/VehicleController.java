package com.ozan.controller;

import com.ozan.dto.DriverDTO;
import com.ozan.dto.ResponseWrapper;
import com.ozan.dto.VehicleDTO;
import com.ozan.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
    @PostMapping
    public ResponseEntity<ResponseWrapper> createVehicle(@RequestBody VehicleDTO vehicle) {
        VehicleDTO vehicleDTO = vehicleService.save(vehicle);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Vehicle is created."
                , HttpStatus.CREATED.value(), vehicleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseWrapper);
    }
    @GetMapping
    public ResponseEntity<ResponseWrapper>getAllVehicles(){
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
    public ResponseEntity<ResponseWrapper>updateVehicle(@PathVariable("vehiclePlateNumber") String plateNumber, @RequestBody VehicleDTO vehicleDTO){
        VehicleDTO updatedVehicleDTO = vehicleService.updateVehicle(vehicleDTO);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Vehicle is updated."
                , HttpStatus.OK.value(), updatedVehicleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }
    @DeleteMapping("/{vehiclePlateNumber}")
    public ResponseEntity<ResponseWrapper>deleteVehicle(@PathVariable("vehiclePlateNumber") String plateNumber){
        vehicleService.delete(plateNumber);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Vehicle is deleted."
                , HttpStatus.OK.value(), null);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);

    }
    @PostMapping("/{vehiclePlateNumber}/assign-driver")
    public ResponseEntity<ResponseWrapper> assignDriver(@PathVariable("vehiclePlateNumber") String plateNumber, @RequestBody DriverDTO driverDTO) {
        VehicleDTO driverAssignedVehicleDTO = vehicleService.assignToDriver(plateNumber,driverDTO.getUserTcId());
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Driver is assigned to vehicle.",
                HttpStatus.OK.value(), driverAssignedVehicleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }




}
