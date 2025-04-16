package com.ozan.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ozan.enums.Status;
import com.ozan.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    @JsonIgnore
    private Long id;

    private String userName;
    private String userTcId;
    private String addressLine;
    private String street;
    private String city;
    private String phoneNumber;
    private String email;
    private Status status;
    private UserType userType;


}






