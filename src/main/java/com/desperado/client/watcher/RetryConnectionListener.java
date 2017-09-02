package com.desperado.client.watcher;

/**
 * Created by desperado on 17-8-23.
 */
public interface RetryConnectionListener {
    void onSuccess();

    void onFail(int retryCounts);
}
