package com.ozan.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO extends UserDTO {

  //  @JsonManagedReference(value = "student-vehicle-reference")
    private VehicleDTO vehicleDTO;

 //   @JsonBackReference(value = "student-school-reference")
    private SchoolDTO schoolDTO;

  //  @JsonManagedReference(value = "student-parent-reference")
    private ParentDTO parentDTO;


}
