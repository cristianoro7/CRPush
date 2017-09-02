package com.desperado.server.action;

import com.desperado.server.protocol.PacketType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by desperado on 17-8-27.
 */
public class HeartBeatAction extends Action {

    private int heartBeatLostCounts;

    private static final int MAX_LOST_COUNTS = 4;

    public byte getMessageType() {
        return PacketType.HEARTBEAT;
    }

    @Override
    public void action(ChannelHandlerContext ctx, Object msg) {
        heartBeatLostCounts = 0; //收到心跳后..重置心跳丢失的次数
    }

    public void updateState(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof IdleStateEvent) {
            System.out.println("服务器收不到心跳包很生气! 第" + heartBeatLostCounts + "次");
            if (heartBeatLostCounts >= MAX_LOST_COUNTS) {
                ctx.close();
                throw new IllegalStateException("服务器收不到心跳包很生气!自己关闭了..让客户端自己去重新连接");
            } else {
                heartBeatLostCounts++;
            }
        } else {
            ctx.fireUserEventTriggered(msg); //
        }
    }
}
