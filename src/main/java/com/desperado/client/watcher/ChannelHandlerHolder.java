package com.desperado.client.watcher;

import io.netty.channel.ChannelHandler;

/**
 * Created by desperado on 17-8-23.
 */
public interface ChannelHandlerHolder {

    ChannelHandler[] getHandlers();
}
