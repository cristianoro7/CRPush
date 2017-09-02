package com.desperado.server.scheduler;

import java.util.concurrent.TimeUnit;

/**
 * Created by desperado on 17-9-1.
 */
public class UnAckTask implements Runnable, Rescheduler {

    private Message message;

    private JobScheduler jobScheduler;

    public UnAckTask(Message message, JobScheduler jobScheduler) {
        this.message = message;
        this.jobScheduler = jobScheduler;
    }

    @Override
    public void run() {
        if (message.getStatus() == Message.Status.SUCCESS) {
            jobScheduler.cancel(message.getMessageId());
            //添加到数据库队列
        }
        int retryCounts = message.getRetryCounts();
        if (retryCounts <= Message.MAX_RETRY_COUNTS) {
            retryCounts += retryCounts;
            message.setRetryCounts(retryCounts);
            reschedule();
        } else {
            jobScheduler.cancel(message.getMessageId());
            //添加到数据库队列
        }
    }

    public String getMessageId() {
        return message.getMessageId();
    }

    @Override
    public void reschedule() {
        jobScheduler.schedule(message, 2, TimeUnit.SECONDS);
    }
}
