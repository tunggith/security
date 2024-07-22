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
/**
 * Utility class for AES encryption and decryption.
 */
public class AESUtil {
    private static Logger logger = Logger.getLogger(AESUtil.class);
    /**
     * Encrypts the given data using AES algorithm.
     *
     * @param data the data to be encrypted
     * @return the encrypted data encoded in Base64, or null if an error occurs
     */
    public static String encrypt(String data) {

        try {
            // Kiểm tra xem dữ liệu có rỗng hay không và ghi lỗi
            if(data.isEmpty()|| Objects.isNull(data)){
                logger.error("data must not be null");
            }
            // Create secret key specification
            SecretKeySpec secretKey = new SecretKeySpec(SecurityContanst.SECRET_AES.getBytes(), SecurityContanst.ALGORITHM_AES);
            // Khởi tạo mật mã cho chế độ mã hóa
            Cipher cipher = Cipher.getInstance(SecurityContanst.ALGORITHM_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            // Thực hiện mã hóa và trả về kết quả được mã hóa trong Base64
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
    /**
     * Decrypts the given encrypted data using AES algorithm.
     *
     * @param encryptedData the encrypted data to be decrypted
     * @return the decrypted data as a string
     * @throws Exception if an error occurs during decryption
     */
    public static String decrypt(String encryptedData) throws Exception {
        // Create secret key specification
        SecretKeySpec secretKey = new SecretKeySpec(SecurityContanst.SECRET_AES.getBytes(), SecurityContanst.ALGORITHM_AES);
        // Khởi tạo mật mã cho chế độ giải mã
        Cipher cipher = Cipher.getInstance(SecurityContanst.ALGORITHM_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // Thực hiện giải mã và trả về kết quả dưới dạng chuỗi
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
    }
}
