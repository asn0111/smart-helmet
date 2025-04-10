package com.asn.smarthelmetproject.repository;

import com.asn.smarthelmetproject.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
