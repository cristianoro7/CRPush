package com.desperado.server.connection;

import io.netty.channel.ChannelId;

import java.util.List;

/**
 * Created by desperado on 17-8-29.
 */
public interface IConnectionManager {

    IConnection addConnection(IConnection connection);

    IConnection removeConnection(ChannelId channelId);

    IConnection getConnection(ChannelId channelId);

    List<IConnection> getConnectionsByTag(String tag);

    int onlineUser();
}
