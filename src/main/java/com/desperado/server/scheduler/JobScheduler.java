package com.desperado.server.scheduler;

import java.util.concurrent.*;

/**
 * Created by desperado on 17-9-1.
 */
public class JobScheduler {

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(7);

    private ConcurrentHashMap<String, Future> taskMap = new ConcurrentHashMap<>();

    public void schedule(Message message, long delay, TimeUnit timeUnit) {
        UnAckTask unAckTask = new UnAckTask(message, this);
        Future future = scheduledExecutorService.schedule(unAckTask, delay, timeUnit);
        taskMap.put(unAckTask.getMessageId(), future);
    }

    public void cancel(String messageId) {
        Future future = taskMap.remove(messageId);
        if (future != null) {
            future.cancel(true);
        }
    }
}
