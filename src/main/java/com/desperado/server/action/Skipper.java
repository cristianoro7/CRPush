package com.desperado.server.action;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by desperado on 17-8-26.
 */
public interface Skipper {

    void skip(ChannelHandlerContext ctx, Object msg);

}
