package com.desperado.access.tools;

import com.desperado.access.wrapper.PushBean;
import com.google.gson.Gson;

/**
 * Created by desperado on 17-9-1.
 */
public final class Gsoner {

    private static Gson gson;

    public static <T> T fromJson(String json, Class<T> clazz) {
        checkGson();
        return gson.fromJson(json, clazz);
    }

    public static String toJson(PushBean bean) {
        checkGson();
        return gson.toJson(bean);
    }

    public static byte[] toByteArray(PushBean bean) {
        return toJson(bean).getBytes();
    }

    private static void checkGson() {
        if (gson == null) {
            gson = new Gson();
        }
    }
}
