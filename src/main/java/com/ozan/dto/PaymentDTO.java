package com.ozan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentDTO {
    @JsonIgnore
    private Long id;

    private BigDecimal amount;

    private LocalDate paymentDate;

    private boolean isAnyUnpaid;

  //  @JsonManagedReference(value = "parent-payment-reference")
    private ParentDTO parentDTO;

}
