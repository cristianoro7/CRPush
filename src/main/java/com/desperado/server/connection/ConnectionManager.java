package com.desperado.server.connection;

import io.netty.channel.ChannelId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by desperado on 17-8-26.
 */
public class ConnectionManager implements IConnectionManager {

    private ConcurrentHashMap<ChannelId, IConnection> connectionHolder = new ConcurrentHashMap<ChannelId, IConnection>();

    private static volatile ConnectionManager INSTANCE;

    public static ConnectionManager getInstance() {
        if (INSTANCE == null) {
            synchronized (ConnectionManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionManager();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public IConnection addConnection(IConnection connection) {
        return connectionHolder.put(connection.getChannel().id(), connection);
    }

    @Override
    public IConnection removeConnection(ChannelId channelId) {
        return connectionHolder.remove(channelId);
    }

    @Override
    public IConnection getConnection(ChannelId channelId) {
        return connectionHolder.get(channelId);
    }

    @Override
    public List<IConnection> getConnectionsByTag(String tag) {
        List<IConnection> connections = new ArrayList<>();
        for (IConnection c : connectionHolder.values()) {
            Set<String> tags = c.getTag();
            if (tags.contains(tag)) {
                connections.add(c);
            }
        }
        return connections;
    }

    @Override
    public int onlineUser() {
        return connectionHolder.size();
    }
}
