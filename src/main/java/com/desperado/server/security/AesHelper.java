package com.desperado.server.security;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * Created by desperado on 17-8-31.
 */
public class AesHelper implements IAes {


    public byte[] encrypt(String key, String content) {
        return crypt(key, Cipher.ENCRYPT_MODE, content.getBytes());
    }

    public byte[] decrypt(String key, String content) {
        return decrypt(key, content.getBytes());
    }

    public byte[] decrypt(String key, byte[] content) {
        return crypt(key, Cipher.DECRYPT_MODE, content);
    }

    private Cipher getInstance() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance("AES");
    }

    private byte[] crypt(String key, int mode, byte[] content) {
        Cipher cipher;
        try {
            cipher = getInstance();
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
            cipher.init(mode, secretKeySpec);
            cipher.update(content);
            return cipher.doFinal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
