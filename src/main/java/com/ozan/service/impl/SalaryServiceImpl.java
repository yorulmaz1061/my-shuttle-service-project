package com.ozan.service.impl;

import com.ozan.dto.SalaryDTO;
import com.ozan.dto.StudentDTO;
import com.ozan.entity.Salary;
import com.ozan.exception.NotFoundException;
import com.ozan.mapper.MapperUtil;
import com.ozan.repository.SalaryRepository;
import com.ozan.service.SalaryService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaryServiceImpl implements SalaryService {
    SalaryRepository salaryRepository;
    MapperUtil mapperUtil;
    
    public SalaryServiceImpl(SalaryRepository salaryRepository, MapperUtil mapperUtil) {
        this.salaryRepository = salaryRepository;
        this.mapperUtil = mapperUtil;
    }
    
    @Override
    public SalaryDTO save(SalaryDTO salary) {
        salaryRepository.save(mapperUtil.convert(salary, new Salary()));
        return mapperUtil.convert(salary, new SalaryDTO()); 
        
    }

    @Override
    public List<SalaryDTO> listAllSalaries() {
        List<Salary> salaryList = salaryRepository.findAll();
        return salaryList.stream().map(salary -> mapperUtil.convert(salary, new SalaryDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public SalaryDTO findDriverSalaryByTcId(String userTcId) {
        List<Salary> driverSalaryList = salaryRepository.findSalaryByDriverUserTcId(userTcId);
        if( userTcId== null) throw new NotFoundException("Driver salary not found.");
        return mapperUtil.convert(driverSalaryList, new SalaryDTO());

    }

}
