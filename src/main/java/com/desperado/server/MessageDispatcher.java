package com.desperado.server;

import com.desperado.server.action.AckAction;
import com.desperado.server.action.Action;
import com.desperado.server.action.HeartBeatAction;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by desperado on 17-8-27.
 */
public class MessageDispatcher implements IMessageDispatcher {

    private static volatile MessageDispatcher INSTANCE;

    private List<Action> actionList = new ArrayList<Action>();

    private Action ackAction = new AckAction();

    private HeartBeatAction heartBeatAction = new HeartBeatAction();


    private MessageDispatcher() {
        actionList.add(heartBeatAction);
//        actionList.add(sessionAction);
        actionList.add(ackAction);
//        actionList.add(singleMessageAction);
        actionList = Collections.unmodifiableList(actionList);
    }

    public static MessageDispatcher getInstance() {
        if (INSTANCE == null) {
            synchronized (MessageDispatcher.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MessageDispatcher();
                }
            }
        }
        return INSTANCE;
    }

    public void dispatch(ChannelHandlerContext ctx, Object msg) {
        for (Action a : actionList) {
//            if (!a.filter(((MessageProto.msg) msg).getType())) {
//                a.action(ctx, msg);
//            }
        }
    }

    public void dispatchHeartBeatMessage(ChannelHandlerContext ctx, Object msg) {
        heartBeatAction.updateState(ctx, msg);
    }

    public void dispatchConnectionRemoveMessage(ChannelHandlerContext ctx) {
//        sessionAction.removeSession(ctx);
    }
}
