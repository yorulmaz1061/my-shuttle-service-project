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
public class Hostess extends BaseEntity {

    private String hostessName;

    @Column(unique = true, nullable = false)
    private String hostessTcId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressAndPhone addressAndPhone;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne (mappedBy = "hostess")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "hostess")
    private List<Salary> salaryList;

}
