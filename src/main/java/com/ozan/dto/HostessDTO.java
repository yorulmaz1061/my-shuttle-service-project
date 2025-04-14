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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HostessDTO {
    @JsonIgnore
    private Long id;

    @NotBlank(message= "Hostess name cannot be empty")
    @Size(min = 1, max = 100, message = "Driver name should be between 1 and 100 characters.")
    private String hostessName;

    @NotBlank(message = "Hostess TC Id cannot be empty")
    @Pattern(regexp = "\\d{11}", message = "Hostess TC Id must be exactly 11 digits")
    private String hostessTcId;


    @JsonBackReference(value = "hostess-vehicle reference")
    private VehicleDTO vehicleDTO;

    @Valid
    @JsonManagedReference("hostess-addressAndPhone-reference")
    private AddressAndPhoneDTO addressAndPhoneDTO;

}
