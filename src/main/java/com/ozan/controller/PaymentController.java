package com.ozan.controller;

import com.ozan.dto.PaymentDTO;
import com.ozan.dto.ResponseWrapper;
import com.ozan.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createPayment(@RequestBody PaymentDTO payment){
        PaymentDTO paymentDTO = paymentService.save(payment);
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Payment is created."
                , 201, paymentDTO);
        return ResponseEntity.status(201).body(responseWrapper);
    }
    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllPayments(){
        List<PaymentDTO> paymentDTOList = paymentService.listAllPayments();
        ResponseWrapper responseWrapper = new ResponseWrapper(true, "Payments are listed."
                , 200, paymentDTOList);
        return ResponseEntity.status(200).body(responseWrapper);
    }



}
