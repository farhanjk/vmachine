package com.vending.machine.app.common.concurrency.threadqueue;


import android.os.Handler;
import android.os.Looper;

public class UiThreadQueue extends ThreadQueue {

    public UiThreadQueue() {
        super(new AndroidHandlerRunner(new Handler(Looper.getMainLooper())));
    }

}
