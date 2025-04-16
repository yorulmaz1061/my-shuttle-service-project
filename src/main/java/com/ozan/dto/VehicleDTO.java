package com.ozan.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ozan.entity.School;
import com.ozan.entity.Student;
import com.ozan.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VehicleDTO {
    @JsonIgnore
    private Long id;

    private String plateNumber;

    private String routeNumber;

    private String make;

    private Boolean isFull;

    private Integer totalStudentCount;

    private Status status;

  //  @JsonManagedReference(value = "driver-vehicle-reference")
    private DriverDTO driverDTO;

 //   @JsonManagedReference(value = "hostess-vehicle-reference")
    private HostessDTO hostessDTO;

 //   @JsonBackReference(value = "vehicle-school-reference")
    private List<School> schoolList;

  //  @JsonBackReference(value = "vehicle-student-reference")
    private Student student;


}
