package com.ozan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDTO {
    @JsonIgnore
    private Long id;

    @NotNull
    private String studentName;
    @NotBlank(message = "Student TC Id cannot be empty")
    @Pattern(regexp = "\\d{11}", message = "Student TC Id must be exactly 11 digits")
    private String studentTcId;

   @JsonManagedReference(value = "student-vehicle reference")
    private VehicleDTO vehicleDTO;


    private SchoolDTO schoolDTO;

    private ParentDTO parentDTO;
}
