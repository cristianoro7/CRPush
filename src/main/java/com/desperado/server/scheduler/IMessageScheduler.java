package com.desperado.server.scheduler;

/**
 * Created by desperado on 17-9-1.
 */
public interface IMessageScheduler {

    void sendMessageSuccess(Message message);

    void sendingMessage(Message message);

    void sendMessageFail(Message message);
}
