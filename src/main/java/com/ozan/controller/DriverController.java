package com.ozan.controller;

import com.ozan.dto.DriverDTO;
import com.ozan.dto.ResponseWrapper;
import com.ozan.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/driver")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }
    @PostMapping
    public ResponseEntity<ResponseWrapper> createDriver(@RequestBody DriverDTO driver){
        DriverDTO driverDTO = driverService.save(driver);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Driver is created."
                , 201, driverDTO);
        return ResponseEntity.status(201).body(responseWrapper);
    }
    @GetMapping
    public ResponseEntity<ResponseWrapper>getAllDrivers(){
        List<DriverDTO> driverDTOList = driverService.listAllDrivers();
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Drivers are listed."
        , HttpStatus.OK.value(), driverDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }
    @GetMapping("/{userTcId}")
    public ResponseEntity<ResponseWrapper>getDriverByTcId(@PathVariable("userTcId") String userTcId){
       DriverDTO driverDTO = driverService.findByUserTcId(userTcId);
       ResponseWrapper responseWrapper = new ResponseWrapper(true, "Driver is listed."
               , HttpStatus.OK.value(), driverDTO);
       return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }
    @PutMapping("/{userTcId}")
    public ResponseEntity<ResponseWrapper>updateDriver(@PathVariable("userTcId") String userTcId, @RequestBody DriverDTO driverDTO){
        DriverDTO updatedDriverDTO = driverService.updateDriver(driverDTO);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Driver is updated."
                , HttpStatus.OK.value(), updatedDriverDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }
    @DeleteMapping("/{userTcId}")
    public ResponseEntity<ResponseWrapper>deleteDriver(@PathVariable("userTcId") String userTcId){
        driverService.delete(userTcId);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Driver is deleted."
                , HttpStatus.OK.value(), null);
        return ResponseEntity.status(HttpStatus.OK).body(responseWrapper);
    }


}
