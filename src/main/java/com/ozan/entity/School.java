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
public class School extends BaseEntity {

    private String schoolName;

    private String contactPersonName;

    private String contactPersonPhone;

    private String schoolAddress;

    private String schoolStreet;

    private String schoolCity;

    @ManyToMany
    @JoinTable(name = "vehicle_school_rel",
            joinColumns = @JoinColumn(name = "school_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<Vehicle> vehicleList;

    @OneToOne(cascade = CascadeType.ALL)
    private Student student;

    private Integer currentTemperature;

}
