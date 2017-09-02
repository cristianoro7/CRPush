package com.desperado.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by desperado on 17-8-25.
 */
public class ClientRegisterHandler extends ChannelInboundHandlerAdapter {

    private String clientId;

    public ClientRegisterHandler(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //成功建立连接后....发送注册信息给服务器, 传送设备名字给服务器
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    }
}
