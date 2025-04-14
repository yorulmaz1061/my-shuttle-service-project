package com.ozan.entity;

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
public class Vehicle extends BaseEntity {

    private String plateNumber;

    private String routeNumber;

    private String make;

    private Boolean isFull;

    private Integer totalStudentCount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hostess_id")
    private Hostess hostess;

    @ManyToMany(mappedBy = "vehicleList")
    private List<School> schoolList;

    @OneToOne(mappedBy = "vehicle")
    private Student student;


}
