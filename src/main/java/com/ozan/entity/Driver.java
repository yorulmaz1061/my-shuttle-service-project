package com.ozan.entity;

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
public class Driver extends User {

    private String driverLicenseNumber;

    @OneToOne (mappedBy = "driver")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "driver")
    private List<Salary> salaryList;

}
