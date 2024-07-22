package com.example.securitytest.request;

import com.example.securitytest.contanst.SecurityContanst;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
/**
 * DTO class for transaction requests.
 * This class is used to transfer encrypted transaction data between client and server.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private String id;
    private String transactionId;
    private String accountTransaction;
    private String indebt;
    private String have;
    private String timeTransaction;
}
