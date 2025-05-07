package com.ozan.service;

import com.ozan.dto.PaymentDTO;
import java.util.List;

public interface PaymentService {
    PaymentDTO save(PaymentDTO payment);

    List<PaymentDTO> listAllPayments();
}
