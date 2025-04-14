package com.ozan.entity;

import com.ozan.enums.Months;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Salary extends BaseEntity {
    private BigDecimal amount;
    private boolean isPaid;
    private Months month;

    @ManyToOne
    private Driver driverSalary;

    @ManyToOne
    private Hostess hostessSalary;


}
