package com.desperado.server.scheduler;

/**
 * Created by desperado on 17-9-1.
 */
public class Message {

    private int status = Status.UNACK;

    private String messageId;

    private String tags;

    private int retryCounts;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static final int MAX_RETRY_COUNTS = 3;

    public Message(String messageId, String tags, String content) {
        this.messageId = messageId;
        this.tags = tags;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public int getRetryCounts() {
        return retryCounts;
    }

    public void setRetryCounts(int retryCounts) {
        this.retryCounts = retryCounts;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public static class Status {

        public static final int SUCCESS = 0;

        public static final int FAIL = 1;

        public static final int UNACK = 2;
    }
}
