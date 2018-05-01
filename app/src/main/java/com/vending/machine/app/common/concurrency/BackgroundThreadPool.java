package com.vending.machine.app.common.concurrency;


public interface BackgroundThreadPool {
    void run(Runnable runnable);
}
