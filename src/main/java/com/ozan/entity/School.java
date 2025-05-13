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

    @OneToMany(mappedBy = "school")
    private List<Vehicle> vehicleList;

    @OneToMany(mappedBy = "school")
    private List<Student> student;

}
