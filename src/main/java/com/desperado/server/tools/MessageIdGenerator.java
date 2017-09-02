package com.desperado.server.tools;

import java.util.UUID;

/**
 * Created by desperado on 17-8-31.
 */
public class MessageIdGenerator {

    public static String generateMessageId() {
        UUID uuid = UUID.randomUUID();
        String msgId = uuid.toString();
        return msgId.replace("-", "");
    }
}
