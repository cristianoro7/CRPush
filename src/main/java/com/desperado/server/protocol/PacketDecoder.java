package com.desperado.server.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * Created by desperado on 17-8-31.
 */
public class PacketDecoder extends ByteToMessageDecoder {

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if (in.readableBytes() < PacketConstant.PACKET_HEADER_LENGTH) {
            return; //包的长度小于头部的长度, 直接退出
        }
        byte magic = in.readByte();
        if (magic != PacketConstant.MAGIC_NUMBER) {
            throw new PacketException("error magic");
        }
        byte version = in.readByte();
        byte msgType = in.readByte();
        byte flag = in.readByte();
        int length = in.readInt();
        if (length > PacketConstant.MAX_LENGTH) {
            throw new PacketException("body`s length is too long");
        }
        if (in.readableBytes() < length) {
            return; //body的字节数不够, 直接退出, 等待下次够了再读
        }
        ByteBuf body = in.readBytes(length);
        String content = body.toString(CharsetUtil.UTF_8);
        out.add(new Packet(new Packet.Header(length, msgType, flag, version, magic), content));
    }
}
