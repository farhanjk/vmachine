package com.vending.machine.app.common.concurrency;

import static org.mockito.Mockito.spy;

public class MockBackgroundThreadPool implements BackgroundThreadPool {

    public static BackgroundThreadPool newInstance() {
        return spy(new MockBackgroundThreadPool());
    }

    private MockBackgroundThreadPool() {
    }

    @Override
    public void run(Runnable runnable) {
        runnable.run();
    }
}
