package com.example.securitytest.controller;

import com.example.securitytest.dto.ResponseTransactionDto;
import com.example.securitytest.entity.TransactionEntity;
import com.example.securitytest.request.TransactionRequest;
import com.example.securitytest.service.ITransactionServiceIMPL;
import com.example.securitytest.util.AESUtil;
import com.example.securitytest.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.security.PublicKey;
/**
 * REST controller for handling transaction-related requests.
 */
@RestController
@RequestMapping("/api/v1")
public class TransactionController {
    @Autowired
    private ITransactionServiceIMPL iTransactionService;
    /**
     * Endpoint to add a new transaction.
     *
     * @param transaction the transaction request containing transaction details
     * @return the response containing the transaction details
     */
    @PostMapping("add")
    private ResponseTransactionDto<TransactionEntity> addTransaction(@RequestBody TransactionRequest transaction){

        try{
            // Initialize encryption utilities
            AESUtil aesUntil = new AESUtil();
            RSAUtil rsaUtil = new RSAUtil();
            // Mã hóa chi tiết giao dịch bằng RSA
            transaction.setTransactionId(rsaUtil.encrypt(transaction.getTransactionId()));
            transaction.setIndebt(rsaUtil.encrypt(transaction.getIndebt()));
            transaction.setHave(rsaUtil.encrypt(transaction.getHave()));
            transaction.setTimeTransaction(rsaUtil.encrypt(transaction.getTimeTransaction()));
            // Mã hóa chi tiết giao dịch tài khoản bằng AES
            transaction.setAccountTransaction(
                    aesUntil.encrypt(transaction.getAccountTransaction())
            );
            // Call  transaction service để add transaction
            return iTransactionService.addTransaction(transaction);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
