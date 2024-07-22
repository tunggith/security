package com.example.securitytest.util;

import com.example.securitytest.contanst.SecurityContanst;

import javax.crypto.Cipher;
import java.math.BigDecimal;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
/**
 * Utility class for RSA encryption and decryption.
 */
public class RSAUtil {
    private static PublicKey publicKey;
    private static PrivateKey privateKey;
    // Khối static để khởi tạo cặp khóa RSA
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

    /**
     * Gets the public key.
     *
     * @return the public key
     */
    public static PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Gets the private key.
     *
     * @return the private key
     */
    public static PrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * Encrypts the given data using the public key.
     *
     * @param data the data to be encrypted
     * @return the encrypted data encoded in Base64
     * @throws Exception if an error occurs during encryption
     */
    public static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(SecurityContanst.ALGORITHM_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    /**
     * Decrypts the given encrypted data using the private key.
     *
     * @param data the encrypted data to be decrypted
     * @return the decrypted data as a string
     * @throws Exception if an error occurs during decryption
     */
    public static String decrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(SecurityContanst.ALGORITHM_RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
    }
}
