package com.desperado.server;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by desperado on 17-8-27.
 */
public interface IMessageDispatcher {

    void dispatch(ChannelHandlerContext ctx, Object msg);

    void dispatchHeartBeatMessage(ChannelHandlerContext ctx, Object msg);

}
