package com.example.securitytest.service;

import com.example.securitytest.contanst.SecurityContanst;
import com.example.securitytest.dto.ResponseTransactionDto;
import com.example.securitytest.entity.TransactionEntity;
import com.example.securitytest.repository.ITransactionRepository;
import com.example.securitytest.util.AESUtil;
import com.example.securitytest.util.RSAUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigDecimal;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;

@Service
public class ITransactionServiceIMPL implements ITransactionService{
    private static Logger logger = Logger.getLogger(ITransactionServiceIMPL.class);
    @Autowired
    private ITransactionRepository reponse;
    @Override
    public ResponseTransactionDto<TransactionEntity> addTransaction(TransactionEntity transaction) {
        AESUtil aesUntil = new AESUtil();
        RSAUtil rsaUtil = new RSAUtil();
        try{
            transaction.setTransactionId(rsaUtil.decrypt(transaction.getTransactionId()));
            System.out.println(transaction.getTransactionId());
            transaction.setIndebt(rsaUtil.decrypt(transaction.getIndebt()));
            transaction.setHave(rsaUtil.decrypt(transaction.getHave()));
            transaction.setTimeTransaction(rsaUtil.decrypt(transaction.getTimeTransaction()));
            transaction.setAccountTransaction(
                    aesUntil.decrypt(transaction.getAccountTransaction())
            );
            System.out.println(transaction.getAccountTransaction());
            reponse.save(transaction);
            return new ResponseTransactionDto<>(HttpStatus.OK.value(), SecurityContanst.MESSANGE_SUCCESS,transaction);
        }catch (Exception e){
            logger.error(e);
            return new ResponseTransactionDto<>(HttpStatus.BAD_REQUEST.value(), SecurityContanst.MESSANGE_ERROR,null);
        }

    }

}
