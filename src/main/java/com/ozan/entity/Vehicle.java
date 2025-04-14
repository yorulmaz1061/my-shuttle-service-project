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
public class Vehicle extends BaseEntity {
    private String plateNumber;

    private String routeNumber;
    private String make;
    private Boolean isFull;
    private Integer totalStudentCount;
    private Boolean isActive;

    @OneToOne(mappedBy = "vehicle")
    private Driver driver;

    @ManyToMany
    @JoinTable(name = "vehicle_school_rel"
    , joinColumns = @JoinColumn(name = "vehicle_id")
    ,inverseJoinColumns = @JoinColumn(name = "school_id"))
    private List<School> schoolList;

}
