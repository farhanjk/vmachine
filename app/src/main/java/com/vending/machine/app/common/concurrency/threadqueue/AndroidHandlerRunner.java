package com.vending.machine.app.common.concurrency.threadqueue;

import android.os.Handler;
import android.os.Looper;

public class AndroidHandlerRunner implements ThreadQueue.ThreadRunner {

    private final Handler handler;

    public AndroidHandlerRunner(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run(Runnable runnable) {
        if (handler.getLooper() == Looper.myLooper()) {
            runnable.run(); //run immediately if running from same thread
        } else {
            handler.post(runnable);
        }
    }

    @Override
    public void runDelayed(Runnable runnable, long delayMilliseconds) {
        handler.postDelayed(runnable, delayMilliseconds);
    }

    @Override
    public void clear() {
        handler.removeCallbacksAndMessages(null);
    }
}
