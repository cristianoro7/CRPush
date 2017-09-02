package com.desperado.server.scheduler;

import com.desperado.server.connection.IConnection;
import com.desperado.server.connection.IConnectionManager;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by desperado on 17-9-1.
 */
public class MessageScheduler implements IMessageScheduler {

    private ConcurrentHashMap<String, Message> sendingQueue = new ConcurrentHashMap<>();

    private LinkedBlockingQueue<Message> failOrSendedQueue = new LinkedBlockingQueue<>();

    private IConnectionManager connectionManager;

    private JobScheduler jobScheduler = new JobScheduler();

    public MessageScheduler(IConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void sendMessageSuccess(Message message) {
        Message m = sendingQueue.remove(message.getMessageId());
        if (m != null) {
            failOrSendedQueue.add(m);
        }
    }

    @Override
    public void sendingMessage(Message message) {
        String tag = message.getTags();
        List<IConnection> connections = connectionManager.getConnectionsByTag(tag);
        for (IConnection c : connections) {
            c.send(message); //发送消息给包含该tag的用户.
            sendingQueue.put(message.getMessageId(), message); //添加进为接受的消息队列
            jobScheduler.schedule(message, 2, TimeUnit.SECONDS); //安排UNACK任务
        }
    }

    @Override
    public void sendMessageFail(Message message) {
        Message m = sendingQueue.remove(message.getMessageId());
        if (m != null) {
            failOrSendedQueue.add(m); //将重发3次还失败的消息移动到待写入到数据库的队列
        }
    }
}
