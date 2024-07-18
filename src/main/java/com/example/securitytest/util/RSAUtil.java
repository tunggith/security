package com.example.securitytest.util;

import com.example.securitytest.contanst.SecurityContanst;

import javax.crypto.Cipher;
import java.math.BigDecimal;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RSAUtil {
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    static {
        try {
            // Sinh cặp khóa RSA
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(SecurityContanst.ALGORITHM_RSA);
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.genKeyPair();

            // Lưu PublicKey và PrivateKey vào biến static
            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức để lấy PublicKey cố định
    public static PublicKey getPublicKey() {
        return publicKey;
    }

    // Phương thức để lấy PrivateKey cố định
    public static PrivateKey getPrivateKey() {
        return privateKey;
    }

    // Phương thức mã hóa dữ liệu
    public static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(SecurityContanst.ALGORITHM_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    // Phương thức giải mã dữ liệu kiểu String
    public static String decrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(SecurityContanst.ALGORITHM_RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
    }
}
