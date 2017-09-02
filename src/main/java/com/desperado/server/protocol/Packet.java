package com.desperado.server.protocol;

/**
 * Created by desperado on 17-8-31.
 */
public class Packet {

    private Header header;

    private String msgId; //type为ACK时, 携带的id

    private String content; //推送的内容

    public Packet(Header header, String content) {
        this.header = header;
        this.content = content;
    }

    public Packet() {
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static class Header {

        private byte magicNumber = 0x77;
        private byte version = 0x1;
        private int length; //body的长度
        private byte messageType; //消息的类型
        private byte flag = 0x0; //是否开启加密 或者是 数据压缩

        public Header(int length, byte messageType, byte flag) {
            this.length = length;
            this.messageType = messageType;
            this.flag = flag;
        }

        public Header(int length, byte messageType) {
            this.length = length;
            this.messageType = messageType;
        }

        public Header() {
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public byte getMessageType() {
            return messageType;
        }

        public void setMessageType(byte messageType) {
            this.messageType = messageType;
        }

        public byte getFlag() {
            return flag;
        }

        public void setFlag(byte flag) {
            this.flag = flag;
        }

        public byte getMagicNumber() {
            return magicNumber;
        }

        public void setMagicNumber(byte magicNumber) {
            this.magicNumber = magicNumber;
        }

        public byte getVersion() {
            return version;
        }

        public void setVersion(byte version) {
            this.version = version;
        }
    }
}
