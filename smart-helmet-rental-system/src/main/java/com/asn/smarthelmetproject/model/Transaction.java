package com.asn.smarthelmetproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Helmet helmet;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double totalFare;
    private boolean isPaid;
}
