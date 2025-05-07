package com.ozan.service;

import com.ozan.dto.SalaryDTO;
import java.util.List;

public interface SalaryService {
    SalaryDTO save(SalaryDTO salary);

    List<SalaryDTO> listAllSalaries();

    SalaryDTO findDriverSalaryByTcId(String userTcId);
}
