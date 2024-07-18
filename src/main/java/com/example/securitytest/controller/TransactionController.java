package com.example.securitytest.controller;

import com.example.securitytest.dto.ResponseTransactionDto;
import com.example.securitytest.entity.TransactionEntity;
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

@RestController
@RequestMapping("/api/v1")
public class TransactionController {
    @Autowired
    private ITransactionServiceIMPL iTransactionService;
    @PostMapping("add")
    public ResponseTransactionDto<TransactionEntity> addTransaction(@RequestBody TransactionEntity transaction){

        try{
            AESUtil aesUntil = new AESUtil();
            RSAUtil rsaUtil = new RSAUtil();
            transaction.setTransactionId(rsaUtil.encrypt(transaction.getTransactionId()));
            System.out.println(transaction.getTransactionId());
            transaction.setIndebt(rsaUtil.encrypt(transaction.getIndebt()));
            transaction.setHave(rsaUtil.encrypt(transaction.getHave()));
            transaction.setTimeTransaction(rsaUtil.encrypt(transaction.getTimeTransaction()));
            transaction.setAccountTransaction(
                    aesUntil.encrypt(transaction.getAccountTransaction())
            );
            System.out.println(transaction.getAccountTransaction());
            return iTransactionService.addTransaction(transaction);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
