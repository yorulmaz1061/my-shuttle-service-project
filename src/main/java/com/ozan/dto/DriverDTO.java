package com.ozan.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ozan.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO {
    @JsonIgnore
    private Long id;

    @NotBlank(message= "Driver name cannot be empty")
    @Size(min = 1, max = 100, message = "Driver name should be between 1 and 100 characters.")
    private String driverName;

    @NotBlank(message = "Driver TC Id cannot be empty")
    @Pattern(regexp = "\\d{11}", message = "Driver TC Id must be exactly 11 digits")
    private String driverTcId;

    private String driverLicenseNumber;

    @JsonBackReference (value = "driver-vehicle reference")
    private VehicleDTO vehicleDTO;

    @Valid
    @JsonManagedReference("driver-addressAndPhone-reference")
    private AddressAndPhoneDTO addressAndPhoneDTO;

}
