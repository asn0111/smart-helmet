package com.asn.smarthelmetproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long userId;
    private String rfidTag;
}
