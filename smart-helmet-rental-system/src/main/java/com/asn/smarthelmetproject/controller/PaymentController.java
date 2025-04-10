package com.asn.smarthelmetproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.asn.smarthelmetproject.model.Payment;
import com.asn.smarthelmetproject.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process/{transactionId}/{amount}")
    public Payment makePayment(@PathVariable String transactionId, @PathVariable double amount) {
        return paymentService.processPayment(transactionId, amount);
    }
}
