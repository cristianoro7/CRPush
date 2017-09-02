package com.desperado.server.connection;

import com.desperado.server.protocol.Packet;
import com.desperado.server.scheduler.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Set;

/**
 * Created by desperado on 17-8-29.
 */
public interface IConnection {

    ChannelFuture send(Packet packet);

    ChannelFuture send(Message message);

    Channel getChannel();

    Set<String> getTag();

    void bindTags(String tag);

    void bindTags(String tag, boolean isAppend);

    void bindTags(Set<String> tags);

    void bindTags(Set<String> tags, boolean isAppend);

    void unbindTag(String tag);

    void unbindTags(Set<String> tags);
}
