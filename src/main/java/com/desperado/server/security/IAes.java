package com.desperado.server.security;

/**
 * Created by desperado on 17-8-31.
 */
public interface IAes {

    byte[] encrypt(String key, String content);

    byte[] decrypt(String key, String content);

    byte[] decrypt(String key, byte[] content);
}
