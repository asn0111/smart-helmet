package com.asn.smarthelmetproject.controller;

import com.asn.smarthelmetproject.model.Helmet;
import com.asn.smarthelmetproject.model.Transaction;
import com.asn.smarthelmetproject.model.User;
import com.asn.smarthelmetproject.repository.HelmetRepository;
import com.asn.smarthelmetproject.repository.TransactionRepository;
import com.asn.smarthelmetproject.repository.UserRepository;
import com.asn.smarthelmetproject.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HelmetRepository helmetRepository;

    @GetMapping("/all")
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @PostMapping("/start")
    public Transaction startRental(@RequestBody TransactionDTO dto) {
        User user = userRepository.findById(dto.getUserId()).orElse(null);
        Helmet helmet = helmetRepository.findByRfidTag(dto.getRfidTag());

        if (user == null || helmet == null || !helmet.isAvailable()) {
            return null; // Optional: handle with ResponseEntity for better error messages
        }

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setHelmet(helmet);
        transaction.setStartTime(LocalDateTime.now());
        transaction.setPaid(false);

        helmet.setAvailable(false);
        helmetRepository.save(helmet);

        return transactionRepository.save(transaction);
    }

    @PostMapping("/end/{transactionId}")
    public String endRental(@PathVariable Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId).orElse(null);
        if (transaction == null) return "Transaction not found!";

        transaction.setEndTime(LocalDateTime.now());
        transaction.setTotalFare(calculateFare(transaction.getStartTime(), transaction.getEndTime()));
        transactionRepository.save(transaction);
        return "Rental ended. Total Fare: ₹" + transaction.getTotalFare();
    }

    private double calculateFare(LocalDateTime start, LocalDateTime end) {
        long minutes = java.time.Duration.between(start, end).toMinutes();
        return minutes * 2; // ₹2 per minute
    }
}
