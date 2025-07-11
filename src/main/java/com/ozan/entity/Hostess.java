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
public class Hostess extends User {

    @OneToOne (mappedBy = "hostess")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "hostess")
    private List<Salary> salaryList;

}
