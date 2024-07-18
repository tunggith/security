package com.example.securitytest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTransactionDto<TransactionEntity> {
    private int statusCode;
    private String message;
    private TransactionEntity data;
}
