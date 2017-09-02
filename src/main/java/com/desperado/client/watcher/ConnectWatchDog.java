package com.desperado.client.watcher;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * Created by desperado on 17-8-23.
 */
@ChannelHandler.Sharable
public class ConnectWatchDog extends ChannelInboundHandlerAdapter implements TimerTask {

    private HashedWheelTimer timer = new HashedWheelTimer();

    private ChannelHandlerContext context;

    private RetryConnectionListener retryConnectionListener;

    private ChannelFuture channelFuture;

    private boolean tryToConnected = true;

    private int retryMaxCounts = 7; //默认重连7次

    private int hasTryCount = 0;

    private int retryTime = 2;

    Bootstrap bootstrap; //引导程序

    public ConnectWatchDog(Bootstrap bootstrap, int retryCounts, boolean tryToConnected,
                           int retryTime) {
        this.bootstrap = bootstrap;
        this.retryMaxCounts = retryCounts;
        this.tryToConnected = tryToConnected;
        this.retryTime = retryTime;
    }

    public ConnectWatchDog(Bootstrap bootstrap, RetryConnectionListener listener) {
        this.bootstrap = bootstrap;
        this.retryConnectionListener = listener;
    }

    public void setRetryConnectionListener(RetryConnectionListener retryConnectionListener) {
        this.retryConnectionListener = retryConnectionListener;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        context = ctx;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx); //连接
        hasTryCount = 0; //更新重试数目
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务器挂掉了");
        timer.newTimeout(this, retryTime, TimeUnit.SECONDS); //重新连接
        super.channelInactive(ctx);
    }

    public void run(Timeout timeout) throws Exception {
        System.out.println("正在尝试重新连接!");
        reconnected();
    }

    private void reconnected() {
        hasTryCount++;
        System.out.println(hasTryCount + "");
        bootstrap.connect().addListener(new ConnectedResultListener());
    }

    private class ConnectedResultListener implements ChannelFutureListener {

        public void operationComplete(ChannelFuture future) throws Exception {
            if (future.isSuccess()) {
                if (retryConnectionListener != null) {
                    retryConnectionListener.onSuccess();
                }
            } else {
                if (hasTryCount <= RetryConnectionConfig.MAX_CONNECTION_COUNT) {
                    timer.newTimeout(ConnectWatchDog.this, retryTime, TimeUnit.SECONDS); //重新连接
                } else {
                    retryConnectionListener.onFail(hasTryCount);
                }
            }
        }
    }
}
