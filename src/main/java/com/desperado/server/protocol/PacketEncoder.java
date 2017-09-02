package com.desperado.server.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by desperado on 17-8-31.
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        Packet.Header header = msg.getHeader();
        if (header.getMagicNumber() != PacketConstant.MAGIC_NUMBER) {
            throw new PacketException("Error packet");
        }
        if (header.getLength() > PacketConstant.MAX_LENGTH) {
            throw new PacketException("content`s too long");
        }
        writeHeader(out, header);
        writeContent(out, msg);
    }

    private void write(ByteBuf out, byte b) {
        out.writeByte(b);
    }

    private void writeHeader(ByteBuf byteBuf, Packet.Header header) {
        write(byteBuf, header.getMagicNumber());
        write(byteBuf, header.getVersion());
        write(byteBuf, header.getMessageType());
        write(byteBuf, header.getFlag());
        byteBuf.writeInt(header.getLength());
    }

    private void writeContent(ByteBuf byteBuf, Packet packet) {
        byte[] body = packet.getContent().getBytes();
        byteBuf.writeBytes(body);
    }
}
