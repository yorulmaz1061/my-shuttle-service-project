package com.ozan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class SchoolDTO {
    @JsonIgnore
    private Long id;

    private String schoolName;

    private String contactPersonName;

    private String contactPersonPhone;

    private String schoolAddress;

    private String schoolStreet;

    private String schoolCity;

 //   @JsonManagedReference(value = "school-vehicle-reference")
    private List<VehicleDTO> vehicleDTOList;

  //  @JsonManagedReference(value = "school-student-reference")
    private StudentDTO studentDTO;

    private Integer currentTemperature;

}
