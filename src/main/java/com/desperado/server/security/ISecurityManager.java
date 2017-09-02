package com.desperado.server.security;

/**
 * Created by desperado on 17-8-31.
 */
public interface ISecurityManager {

    String generatedKey2();

    String genAndEncryptKey2ByPrivateKey();

    String decryptKey1ByPrivateKey(String key1);

    byte[] encryptBody(String body);

    byte[] decryptBody(byte[] body);

    byte[] decryptBody(String body);
}
