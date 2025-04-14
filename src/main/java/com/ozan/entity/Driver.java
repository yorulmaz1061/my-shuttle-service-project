package com.ozan.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ozan.enums.Status;
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

    private String driverLicenseNumber;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne (mappedBy = "driver")
    private Vehicle vehicle;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="adress_id")
    private AddressAndPhone addressAndPhone;





}
