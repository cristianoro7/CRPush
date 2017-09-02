package com.desperado.server.protocol;

/**
 * Created by desperado on 17-8-31.
 */
public class PacketType {

    public static final byte HEARTBEAT = 0x0; //心跳

    public static final byte ACK = 0x1; //ACK

    public static final byte HANDSHAKE = 0x2; //握手生成密钥, 用于消息加密

    public static final byte FAST_CONNECTION = 0x3; //快速重连(不需要握手)

    public static final byte BIND = 0x4; //绑定TAG

    public static final byte UNBIND = 0x5; //解绑TAG

    public static final byte PUSH = 0x6; //推送消息

}
