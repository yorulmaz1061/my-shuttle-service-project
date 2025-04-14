package com.ozan.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Student extends BaseEntity {
    private String studentName;
    private String studentTcId;
    private String studentAddress;
    private String studentPhone;

    @OneToOne
    private Vehicle vehicle;
    @OneToOne
    private School school;

    @ManyToOne
    private Parent parent;



}
