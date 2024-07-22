package com.example.securitytest.service;

import com.example.securitytest.dto.ResponseTransactionDto;
import com.example.securitytest.entity.TransactionEntity;
import com.example.securitytest.request.TransactionRequest;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.List;

@Service
public interface ITransactionService {
    ResponseTransactionDto<TransactionEntity> addTransaction(TransactionRequest transaction);
}
