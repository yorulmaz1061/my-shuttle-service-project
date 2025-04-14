package com.ozan.entity;

import com.ozan.enums.Months;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Where(clause = "is_deleted=false")
public class Payment extends BaseEntity {
    private BigDecimal amount;
    private Months month;
    private boolean isPaid;

    @ManyToOne
    private Parent parent;





}
