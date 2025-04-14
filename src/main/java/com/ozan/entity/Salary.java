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

    @Enumerated(EnumType.STRING)
    private Months month;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hostess_id")
    private Hostess hostess;


}
