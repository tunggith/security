package com.example.securitytest.service;

import com.example.securitytest.contanst.SecurityContanst;
import com.example.securitytest.dto.ResponseTransactionDto;
import com.example.securitytest.entity.TransactionEntity;
import com.example.securitytest.repository.ITransactionRepository;
import com.example.securitytest.request.TransactionRequest;
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
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of the ITransactionService interface.
 * Handles business logic related to transactions.
 */
@Service
public class ITransactionServiceIMPL implements ITransactionService{
    private static Logger logger = Logger.getLogger(ITransactionServiceIMPL.class);
    @Autowired
    private ITransactionRepository reponse;
    /**
     * Adds a new transaction after decrypting its details.
     *
     * @param transaction the transaction request containing encrypted transaction details
     * @return a response DTO containing the transaction details and status
     */
    @Override
    public ResponseTransactionDto<TransactionEntity> addTransaction(TransactionRequest transaction) {
        try{
            // Tạo một thực thể giao dịch mới để lưu trữ các giá trị được giải mã
            TransactionEntity transactionEntity = new TransactionEntity();

            AESUtil aesUntil = new AESUtil();
            RSAUtil rsaUtil = new RSAUtil();
            transactionEntity.setTransactionId(rsaUtil.decrypt(transaction.getTransactionId()));
            transactionEntity.setIndebt(new BigDecimal(rsaUtil.decrypt(transaction.getIndebt())));
            transactionEntity.setHave(rsaUtil.decrypt(transaction.getHave()));
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            transactionEntity.setTimeTransaction(
                    new Date(String.valueOf(formatDate.parse(rsaUtil.decrypt(transaction.getTimeTransaction()))))
            );
            transactionEntity.setAccountTransaction(
                    aesUntil.decrypt(transaction.getAccountTransaction())
            );
            // Lưu thực thể giao dịch được giải mã vào kho lưu trữ
            reponse.save(transactionEntity);
            return new ResponseTransactionDto<>(HttpStatus.OK.value(), SecurityContanst.MESSANGE_SUCCESS,transactionEntity);
        }catch (NoSuchPaddingException e) {
            logger.error(e);
            return new ResponseTransactionDto<>(HttpStatus.BAD_REQUEST.value(), SecurityContanst.MESSANGE_ERROR,null);
        } catch (IllegalBlockSizeException e) {
            logger.error(e);
            return new ResponseTransactionDto<>(HttpStatus.BAD_REQUEST.value(), SecurityContanst.MESSANGE_ERROR,null);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
            return new ResponseTransactionDto<>(HttpStatus.BAD_REQUEST.value(), SecurityContanst.MESSANGE_ERROR,null);
        } catch (BadPaddingException e) {
            logger.error(e);
            return new ResponseTransactionDto<>(HttpStatus.BAD_REQUEST.value(), SecurityContanst.MESSANGE_ERROR,null);
        } catch (InvalidKeyException e) {
            logger.error(e);
            return new ResponseTransactionDto<>(HttpStatus.BAD_REQUEST.value(), SecurityContanst.MESSANGE_ERROR,null);
        }catch (NullPointerException e) {
            logger.error(e);
            return new ResponseTransactionDto<>(HttpStatus.BAD_REQUEST.value(), SecurityContanst.MESSANGE_ERROR,null);
        } catch (ParseException e) {
            logger.error(e);
            return new ResponseTransactionDto<>(HttpStatus.BAD_REQUEST.value(), SecurityContanst.MESSANGE_ERROR,null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
