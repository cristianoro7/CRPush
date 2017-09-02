package com.desperado.server.connection;

import com.desperado.server.protocol.Packet;
import com.desperado.server.protocol.PacketType;
import com.desperado.server.scheduler.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by desperado on 17-8-29.
 */
public class Connection implements IConnection {

    private Channel channel;

    private Set<String> tags;

    public Connection(Channel channel) {
        this.channel = channel;
        this.tags = new HashSet<>();
    }

    @Override
    public ChannelFuture send(Packet packet) {
        if (channel.isActive()) {
            return channel.writeAndFlush(packet);
        }
        return null;
    }

    @Override
    public ChannelFuture send(Message message) {
        Packet.Header header = new Packet.Header(message.getContent().getBytes().length,
                PacketType.PUSH);
        Packet packet = new Packet(header, message.getContent());
        return send(packet);
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public Set<String> getTag() {
        return tags;
    }

    @Override
    public void bindTags(String tag) {
        bindTags(tag, true);
    }

    @Override
    public void bindTags(String tag, boolean isAppend) {
        if (isAppend) {
            this.tags.add(tag);
        } else {
            this.tags.clear();
            this.tags.add(tag);
        }
    }

    @Override
    public void bindTags(Set<String> tags) {
        bindTags(tags, true);
    }

    @Override
    public void bindTags(Set<String> tags, boolean isAppend) {
        if (isAppend) {
            this.tags.addAll(tags);
        } else {
            this.tags.clear();
            this.tags.addAll(tags);
        }
    }

    @Override
    public void unbindTag(String tag) {
        tags.remove(tag);
    }

    @Override
    public void unbindTags(Set<String> tags) {
        this.tags.removeAll(tags);
    }
}
