package com.ozan.entity;

import com.ozan.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Vehicle extends BaseEntity {
    @Column(unique=true)
    private String plateNumber;

    private String routeNumber;

    private String make;

    private Boolean isFull;

    private Integer totalStudentCount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "hostess_id")
    private Hostess hostess;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Student> studentList;


}
