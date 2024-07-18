package com.example.securitytest.util;

import com.example.securitytest.contanst.SecurityContanst;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {

    public static String encrypt(String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SecurityContanst.SECRET_AES.getBytes(), SecurityContanst.ALGORITHM_AES);
        Cipher cipher = Cipher.getInstance(SecurityContanst.ALGORITHM_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SecurityContanst.SECRET_AES.getBytes(), SecurityContanst.ALGORITHM_AES);
        Cipher cipher = Cipher.getInstance(SecurityContanst.ALGORITHM_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
    }
}
