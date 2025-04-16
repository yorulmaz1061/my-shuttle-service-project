package com.ozan.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalaryDTO {
    @JsonIgnore
    private Long id;

    private BigDecimal amount;

    private boolean isAnyUnpaid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate salaryDate;

 //   @JsonManagedReference(value = "driver-salary-reference")
    private DriverDTO driverDTO;

 //   @JsonManagedReference(value = "hostess-salary-reference")
    private HostessDTO hostessDTO;

}
