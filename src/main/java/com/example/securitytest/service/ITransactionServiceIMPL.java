package com.example.securitytest.service;

import com.example.securitytest.dto.ResponseTransactionDto;
import com.example.securitytest.entity.TransactionEntity;
import com.example.securitytest.repository.ITransactionRepository;
import com.example.securitytest.util.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ITransactionServiceIMPL implements ITransactionService{
    @Autowired
    private ITransactionRepository reponse;
    @Override
    public ResponseTransactionDto<TransactionEntity> addTransaction(TransactionEntity transaction) {
        AESUtil aesUntil = new AESUtil();
        try{
            transaction.setAccountTransaction(
                    aesUntil.decrypt(transaction.getAccountTransaction())
            );
            System.out.println(transaction.getAccountTransaction());
            reponse.save(transaction);
            return new ResponseTransactionDto<>(HttpStatus.OK.value(), "success",transaction);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseTransactionDto<>(HttpStatus.BAD_REQUEST.value(), "error",null);
        }
    }


}
