package com.ozan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StudentDTO extends UserDTO {
    @NotBlank
    private String parentFirstName;

    @NotBlank
    private String parentLastName;

    @NotBlank
    private String parentTcId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VehicleDTO vehicleDTO;

    @NotBlank
    private SchoolDTO schoolDTO;
}

