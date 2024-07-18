package com.example.securitytest.controller;

import com.example.securitytest.dto.ResponseTransactionDto;
import com.example.securitytest.entity.TransactionEntity;
import com.example.securitytest.service.ITransactionServiceIMPL;
import com.example.securitytest.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {
    @Autowired
    private ITransactionServiceIMPL iTransactionService;
    @PostMapping("add")
    public ResponseTransactionDto<TransactionEntity> addTransaction(@RequestBody TransactionEntity transaction){

        try{
            AESUtil aesUntil = new AESUtil();
            transaction.setAccountTransaction(
                    aesUntil.encrypt(transaction.getAccountTransaction())
            );
            System.out.println(transaction.getAccountTransaction());
            return iTransactionService.addTransaction(transaction);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
