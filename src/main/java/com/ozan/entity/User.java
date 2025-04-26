package com.ozan.entity;

import com.ozan.enums.Status;
import com.ozan.enums.UserType;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public class User extends BaseEntity{

    private String userFirstName;

    private String userLastName;

    @Column(unique=true)
    private String userTcId;

    private String addressLine;

    private String street;

    private String city;

    private String phoneNumber;

    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private UserType userType;

}
