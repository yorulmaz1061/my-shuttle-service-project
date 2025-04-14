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
public class Hostess extends BaseEntity {

    private String hostessName;

    @Column(unique = true, nullable = false)
    private String hostessTcId;

    private String hostessAddress;

    private String hostessPhoneNumber;

    private Boolean isActive;

    @OneToOne
    private Vehicle vehicle;

}
