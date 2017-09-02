package com.desperado.server.security;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by desperado on 17-8-31.
 */
public class RsaHelper implements IRsa {

    private static final String KEY_RSA = "RSA";

    private static final int MODE_PRIVATE = 0;

    private static final int MODE_PUBLIC = 1;

    public byte[] encryptByPublicKey(byte[] content, byte[] publicKey) {
        return crypt(Cipher.ENCRYPT_MODE, MODE_PUBLIC, content, publicKey);
    }

    public byte[] encryptByPrivateKey(byte[] content, byte[] privateKey) {
        return crypt(Cipher.ENCRYPT_MODE, MODE_PRIVATE, content, privateKey);
    }

    public byte[] decryptByPublicKey(byte[] content, byte[] publicKey) {
        return crypt(Cipher.DECRYPT_MODE, MODE_PUBLIC, content, publicKey);
    }

    public byte[] decryptByPrivateKey(byte[] content, byte[] privateKey) {
        return crypt(Cipher.DECRYPT_MODE, MODE_PRIVATE, content, privateKey);
    }

    private byte[] crypt(int encryptOrDecrypt, int mode, byte[] content, byte[] key) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_RSA);
            Key k;
            if (mode == MODE_PUBLIC) {
                k = keyFactory.generatePublic(new X509EncodedKeySpec(key));
            } else {
                k = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(key));
            }

            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(encryptOrDecrypt, k);
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private KeyPair getKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_RSA);
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }
}
