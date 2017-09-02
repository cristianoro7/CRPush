package com.desperado.server.action;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by desperado on 17-8-27.
 */
public abstract class Action implements Skipper, Filter {

    @Override
    public boolean filter(byte messageType) {
        return getMessageType() != messageType;
    }

    @Override
    public void skip(ChannelHandlerContext ctx, Object msg) {
        ctx.fireChannelRead(msg);
    }

    abstract public byte getMessageType();

    abstract public void action(ChannelHandlerContext ctx, Object msg);

}
