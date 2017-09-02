package com.desperado.client;

import com.desperado.client.watcher.ChannelHandlerHolder;
import com.desperado.client.watcher.ConnectWatchDog;
import com.desperado.client.watcher.RetryConnectionListener;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;

import java.net.InetSocketAddress;
import java.util.Random;

/**
 * Created by desperado on 17-8-23.
 */
public class BootstrapManager implements ChannelHandlerHolder, RetryConnectionListener {

    private Bootstrap bootstrap = null;

    private ConnectWatchDog connectWatchDog;

    private EventLoopGroup eventLoopGroup;

    private Class<? extends Channel> channelClass;

    private int port;

    private String host;

    private int tempClient = 0;

    public BootstrapManager() {
        bootstrap = new Bootstrap();
        connectWatchDog = new ConnectWatchDog(bootstrap, this);
    }

    public BootstrapManager eventLoopGroup(EventLoopGroup eventExecutors) {
        this.eventLoopGroup = eventExecutors;
        return this;
    }

    public BootstrapManager channel(Class<? extends Channel> channelClass) {
        this.channelClass = channelClass;
        return this;
    }

    public BootstrapManager ip(String host, int port) {
        this.host = host;
        this.port = port;
        return this;
    }

    public ChannelHandler[] getHandlers() {
        return providesHandlers();
    }

    private ChannelHandler[] providesHandlers() {
        int c = new Random().nextInt();
        tempClient = 1;
        return new ChannelHandler[]{
        };
    }

    public void run() {
        bootstrap.group(eventLoopGroup)
                .channel(channelClass)
                .remoteAddress(new InetSocketAddress(host, port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(getHandlers());
                    }
                });
        try {
            bootstrap.connect().sync(); //阻塞当前线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onSuccess() {
        System.out.println("重新连接成功!");
    }

    public void onFail(int retryCount) {
        eventLoopGroup.shutdownGracefully(); //释放掉资源
        System.exit(0); //重试了7次都失败...那就退出系统吧...因为服务器出了问题
    }
}
