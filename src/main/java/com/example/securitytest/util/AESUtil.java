package com.example.securitytest.util;

import com.example.securitytest.contanst.SecurityContanst;
import org.apache.log4j.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

public class AESUtil {
    private static Logger logger = Logger.getLogger(AESUtil.class);
    public static String encrypt(String data) {
        if(data.isEmpty()|| Objects.isNull(data)){
            logger.error("data is null");
        }
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SecurityContanst.SECRET_AES.getBytes(), SecurityContanst.ALGORITHM_AES);
            Cipher cipher = Cipher.getInstance(SecurityContanst.ALGORITHM_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        }catch (NoSuchPaddingException e) {
            logger.error(e);
            return null;
        } catch (IllegalBlockSizeException e) {
            logger.error(e);
            return null;
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
            return null;
        } catch (BadPaddingException e) {
            logger.error(e);
            return null;
        } catch (InvalidKeyException e) {
            logger.error(e);
            return null;
        }catch (NullPointerException e) {
            logger.error(e);
            return null;
        }
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SecurityContanst.SECRET_AES.getBytes(), SecurityContanst.ALGORITHM_AES);
        Cipher cipher = Cipher.getInstance(SecurityContanst.ALGORITHM_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
    }
}
