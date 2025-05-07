package com.ozan.controller;

import com.ozan.dto.ResponseWrapper;
import com.ozan.dto.SalaryDTO;
import com.ozan.service.SalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salary")
public class SalaryController {
    private final SalaryService salaryService;
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }
    @PostMapping
    public ResponseEntity<ResponseWrapper> createSalary(SalaryDTO salary){
        SalaryDTO salaryDTO = salaryService.save(salary);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Salary is created."
                , 201, salaryDTO);
        return ResponseEntity.status(201).body(responseWrapper);


    }
    @GetMapping
    public ResponseEntity<ResponseWrapper>getAllSalaries(){
        List<SalaryDTO> salaryDTOList = salaryService.listAllSalaries();
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Salaries are retrieved"
                , HttpStatus.OK.value(), salaryDTOList);
        return ResponseEntity.status(200).body(responseWrapper);
    }
    @GetMapping("/{userTcId}")
    public ResponseEntity<ResponseWrapper>getDriverSalaryByTcId(@PathVariable("userTcId") String userTcId){
        SalaryDTO salaryDTO = salaryService.findDriverSalaryByTcId(userTcId);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Salary is retrieved"
                , HttpStatus.OK.value(), salaryDTO);
        return ResponseEntity.status(200).body(responseWrapper);
    }


}
