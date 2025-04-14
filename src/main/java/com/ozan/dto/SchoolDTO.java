package com.ozan.dto;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SchoolDTO {
    @JsonIgnore
    private Long id;

    @NotNull
    private String schoolName;

    private String contactPersonName;

    @JsonManagedReference("school-vehicle-reference")
    private List<VehicleDTO> vehicleDTOList;

    @Valid
    @JsonManagedReference("school-addressAndPhone-reference")
    private AddressAndPhoneDTO addressAndPhoneDTO;


}
