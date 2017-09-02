package com.desperado.server.security;

import io.netty.util.CharsetUtil;

import java.util.Random;

/**
 * Created by desperado on 17-8-31.
 */
public class SecurityManager implements ISecurityManager {

    private IAes aes;

    private IRsa rsa;

    private String key1;

    private String key2;

    private String aesPrivateKey;

    public SecurityManager() {
        aes = new AesHelper();
        rsa = new RsaHelper();
    }


    @Override
    public String generatedKey2() {
        Random random = new Random();
        return random.nextInt() + "";
    }

    @Override
    public String genAndEncryptKey2ByPrivateKey() {
        key2 = generatedKey2();
        byte[] b = rsa.encryptByPrivateKey(key2.getBytes(), KeyStore.getPrivateKey());
        return new String(b, CharsetUtil.UTF_8);
    }

    @Override
    public String decryptKey1ByPrivateKey(String key1) {
        byte[] b = rsa.decryptByPrivateKey(key1.getBytes(), KeyStore.getPrivateKey());
        key1 = new String(b, CharsetUtil.UTF_8);
        return key1;
    }

    @Override
    public byte[] encryptBody(String body) {
        return aes.encrypt(getPrivateKey(), body);
    }

    @Override
    public byte[] decryptBody(byte[] body) {
        return aes.decrypt(getPrivateKey(), body);
    }

    @Override
    public byte[] decryptBody(String body) {
        return decryptBody(body.getBytes());
    }

    private String getPrivateKey() {
        if (key1 == null || key2 == null) {
            throw new IllegalStateException("key1 or key2 can not be null");
        }
        if (aesPrivateKey == null) {
            aesPrivateKey = key1 + key2;
        }
        return aesPrivateKey;
    }
}
