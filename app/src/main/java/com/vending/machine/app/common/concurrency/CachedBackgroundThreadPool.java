package com.vending.machine.app.common.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedBackgroundThreadPool implements BackgroundThreadPool {

    private final ExecutorService executorService;

    public CachedBackgroundThreadPool() {
        executorService = Executors.newCachedThreadPool();
    }

    public void run(Runnable runnable) {
        executorService.execute(runnable);
    }
}
