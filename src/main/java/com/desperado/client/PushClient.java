package com.desperado.client;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by desperado on 17-8-21.
 */
public class PushClient {

    public static void main(String[] args) {
        new BootstrapManager()
                .eventLoopGroup(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .ip("127.0.0.1", 8012)
                .run(); //启动客户端
    }
}
