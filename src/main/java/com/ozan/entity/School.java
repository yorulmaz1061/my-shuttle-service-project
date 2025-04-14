package com.ozan.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class School extends BaseEntity {
    private String schoolName;
    private String schoolAddress;
    private String schoolCity;
    private String contactPersonName;
    private String contactPersonPhone;

    @ManyToMany(mappedBy = "schoolList")
    private List<Vehicle> vehicleList;

}
