package com.ozan.service.impl;

import com.ozan.dto.PaymentDTO;
import com.ozan.entity.Payment;
import com.ozan.mapper.MapperUtil;
import com.ozan.repository.PaymentRepository;
import com.ozan.service.PaymentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final MapperUtil mapperUtil;

    public PaymentServiceImpl(PaymentRepository paymentRepository, MapperUtil mapperUtil) {
        this.paymentRepository = paymentRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public PaymentDTO save(PaymentDTO payment) {
        Payment savedPayment = paymentRepository.save(mapperUtil.convert(payment, new Payment()));
        return mapperUtil.convert(savedPayment, new PaymentDTO());
    }

    @Override
    public List<PaymentDTO> listAllPayments() {
       List<Payment> paymentList = paymentRepository.findAll();
       return paymentList.stream().map(payment -> mapperUtil.convert(payment, new PaymentDTO()))
               .collect(java.util.stream.Collectors.toList());
    }

}
