package com.ozan.entity;

import com.ozan.enums.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressAndPhone extends BaseEntity {
    private String addressLine;
    private String street;
    private String city;
    private String phoneNumber;
    private String email;
    private Integer currentTemperature;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @OneToOne(mappedBy="addressAndPhone")
    private Student student;

    @OneToOne(mappedBy="addressAndPhone")
    private Parent parent;

    @OneToOne(mappedBy="addressAndPhone")
    private Hostess hostess;

    @OneToOne(mappedBy="addressAndPhone")
    private Driver driver;

    @OneToOne(mappedBy="addressAndPhone")
    private School school;









}
