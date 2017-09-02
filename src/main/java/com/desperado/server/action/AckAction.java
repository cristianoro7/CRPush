package com.desperado.server.action;

import com.desperado.server.protocol.PacketType;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by desperado on 17-8-27.
 */
public class AckAction extends Action {

    @Override
    public byte getMessageType() {
        return PacketType.ACK;
    }

    @Override
    public void action(ChannelHandlerContext ctx, Object msg) {

    }
}
