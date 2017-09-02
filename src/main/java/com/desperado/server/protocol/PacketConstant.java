package com.desperado.server.protocol;

/**
 * Created by desperado on 17-8-31.
 */
public final class PacketConstant {
    public static final byte MAGIC_NUMBER = 0x77;
    public static final int PACKET_HEADER_LENGTH = 8;
    public static final int MAX_LENGTH = 2 ^ 31 - 1;
}
