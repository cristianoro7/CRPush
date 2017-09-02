package com.desperado.access.wrapper;

/**
 * Created by desperado on 17-9-1.
 */
public class PushBean {

    private String msg;

    private String tag;

    public PushBean(String msg, String tag) {
        this.msg = msg;
        this.tag = tag;
    }

    public PushBean() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
