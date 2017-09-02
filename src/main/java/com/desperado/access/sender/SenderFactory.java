package com.desperado.access.sender;

/**
 * Created by desperado on 17-9-1.
 */
public class SenderFactory implements ISenderFactory {

    @Override
    public ISender getDefaultSender() {
        return Sender.defaultSender();
    }
}
