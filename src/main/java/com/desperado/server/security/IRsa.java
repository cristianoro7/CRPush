package com.desperado.server.security;

/**
 * Created by desperado on 17-8-31.
 */
public interface IRsa {

    byte[] encryptByPublicKey(byte[] content, byte[] publicKey);

    byte[] encryptByPrivateKey(byte[] content, byte[] privateKey);

    byte[] decryptByPublicKey(byte[] content, byte[] publicKey);

    byte[] decryptByPrivateKey(byte[] content, byte[] privateKey);
}
