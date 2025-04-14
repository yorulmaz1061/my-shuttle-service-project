package com.ozan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class ParentDTO {
    @JsonIgnore
    private Long id;

    @NotBlank(message= "Parent name cannot be empty")
    @Size(min = 1, max = 100, message = "Parent name should be between 1 and 100 characters.")
    private String parentName;

    @NotBlank(message = "Parent TC Id cannot be empty")
    @Pattern(regexp = "\\d{11}", message = "Parent TC Id must be exactly 11 digits")
    private String parentTcId;

    @Valid
    @JsonManagedReference("parent-addressAndPhone-reference")
    private AddressAndPhoneDTO addressAndPhoneDTO;

}
