package com.ozan.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ozan.entity.Driver;
import com.ozan.entity.School;
import com.ozan.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDTO {
    @JsonIgnore
    private Long id;

    @NotNull
    private String plateNumber;

    private String routeNumber;

    private String make;

    private Boolean isFull;

    private Integer totalStudentCount;

    private Boolean isActive;

    @JsonManagedReference(value = "driver-vehicle reference")
    private DriverDTO driverDTO;

    @JsonBackReference(value = "school-vehicle-reference")
    private List<School> schoolList;

    @JsonBackReference(value = "student-vehicle-reference")
    private List<Student> studentList;
}
