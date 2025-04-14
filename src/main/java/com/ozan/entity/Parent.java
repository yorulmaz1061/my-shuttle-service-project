package com.ozan.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Parent extends BaseEntity {
    private String parentName;
    private String parentTcId;
    private String parentPhoneNumber;
    private String email;
    private String parentAddress;
    private String parentStreet;
    private String parentCity;

}
