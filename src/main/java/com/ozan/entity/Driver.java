package com.ozan.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Driver extends BaseEntity {

    private String driverName;

    @Column(unique = true, nullable = false)
    private String driverTcId;

    private String driverAddress;

    private String driverPhoneNumber;

    private String driverLicenseNumber;

    private Boolean isActive;

    @OneToOne
    private Vehicle vehicle;





}
