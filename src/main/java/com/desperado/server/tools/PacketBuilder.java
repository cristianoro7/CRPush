package com.desperado.server.tools;

import com.desperado.server.protocol.Packet;

/**
 * Created by desperado on 17-9-1.
 */
public class PacketBuilder {

    private Packet.Header header;

    private Packet packet;

    private PacketBuilder() {
        header = new Packet.Header();
        packet = new Packet();
    }

    public static PacketBuilder newBuilder() {
        return new PacketBuilder();
    }

    public PacketBuilder type(byte type) {
        header.setMessageType(type);
        return this;
    }

    public PacketBuilder bodyLength(int length) {
        header.setLength(length);
        return this;
    }

    public PacketBuilder body(String body) {
        packet.setContent(body);
        return this;
    }

    public Packet build() {
        packet.setHeader(header);
        return packet;
    }
}
