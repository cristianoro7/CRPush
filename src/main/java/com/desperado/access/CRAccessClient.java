package com.desperado.access;

import com.desperado.access.sender.ISenderFactory;
import com.desperado.access.sender.SenderFactory;
import com.desperado.access.tools.Gsoner;
import com.desperado.access.wrapper.PushBean;

import java.util.List;
import java.util.Map;

/**
 * Created by desperado on 17-8-26.
 */
public class CRAccessClient {

    private static ISenderFactory senderFactory = new SenderFactory();

    public static void send(String tag, String msg) {
        send(new PushBean(msg, tag));
    }

    public static void send(PushBean bean) {
        send(Gsoner.toJson(bean));
    }

    public static void send(String json) {
        send(json.getBytes());
    }

    public static void send(byte[] body) {
        senderFactory.getDefaultSender().send(body);
    }

    public static void send(List<String> tags, String msg) {
        for (String tag : tags) {
            send(tag, msg);
        }
    }

    public static void send(Map<String, String> map) {
        for (Map.Entry<String, String> e : map.entrySet()) {
            send(e.getKey(), e.getValue());
        }
    }

    public static void send(List<PushBean> pushBeanList) {
        for (PushBean bean : pushBeanList) {
            send(bean);
        }
    }
}
