package com.desperado.server.action;

/**
 * Created by desperado on 17-8-26.
 */
public interface Filter {
    boolean filter(byte messageType);
}
