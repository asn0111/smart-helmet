package com.asn.smarthelmetproject.service;

import com.asn.smarthelmetproject.model.Payment;
import com.asn.smarthelmetproject.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(String transactionId, double amount) {
        Payment payment = new Payment();
        payment.setTransactionId(transactionId);
        payment.setAmount(amount);
        payment.setSuccess(true); // Simulate success

        return paymentRepository.save(payment);
    }
}
