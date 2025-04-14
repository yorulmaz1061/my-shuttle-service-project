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
public class Parent extends BaseEntity {

    private String parentName;

    private String parentTcId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressAndPhone addressAndPhone;

    @OneToMany(mappedBy = "parent")
    private List<Payment> paymentList;

    @OneToMany(mappedBy = "parent")
    private List<Student> studentList;



}
