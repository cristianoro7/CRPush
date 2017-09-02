package com.desperado.server.action;

import com.desperado.server.protocol.PacketType;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by desperado on 17-9-1.
 */
public class UnbindTagAction extends Action {

    @Override
    public byte getMessageType() {
        return PacketType.UNBIND;
    }

    @Override
    public void action(ChannelHandlerContext ctx, Object msg) {

    }
}
