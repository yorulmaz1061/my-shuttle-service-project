package com.ozan.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ozan.entity.*;
import com.ozan.enums.AddressType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddressAndPhoneDTO {
    @JsonIgnore
    private Long id;

    private String addressLine;

    private String street;

    @NotBlank(message="City cannot be empty")
    @Size(min = 1, max = 50, message = "City should be between 1 and 50 characters.")
    private String city;

    private String phoneNumber;

    private String email;

    private Integer currentTemperature;

    private AddressType addressType;

    @JsonBackReference(value = "student-addressAndPhone-reference")
    private StudentDTO studentDTO;

    @JsonBackReference(value = "parent-addressAndPhone-reference")
    private ParentDTO parentDTO;

    @JsonBackReference(value = "hostess-addressAndPhone-reference")
    private HostessDTO hostessDTO;

    @JsonBackReference(value = "driver-addressAndPhone-reference")
    private DriverDTO driverDTO;

    @JsonBackReference(value = "school-addressAndPhone-reference")
    private SchoolDTO schoolDTO;
}
