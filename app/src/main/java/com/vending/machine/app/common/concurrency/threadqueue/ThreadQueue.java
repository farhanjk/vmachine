package com.vending.machine.app.common.concurrency.threadqueue;


public class ThreadQueue {

    private boolean isEnabled;
    private ThreadRunner threadRunner;

    public interface ThreadRunner {
        void run(Runnable runnable);

        void runDelayed(Runnable runnable, long delayMilliseconds);

        void clear();
    }

    public ThreadQueue(ThreadRunner threadRunner) {
        this.threadRunner = threadRunner;
    }

    public void run(Runnable runnable) {
        if (isEnabled()) {
            threadRunner.run(runnable);
        }
    }

    public void runDelayed(Runnable runnable, long delayMilliseconds) {
        if (isEnabled()) {
            threadRunner.runDelayed(runnable, delayMilliseconds);
        }
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public ThreadQueue setEnabled(boolean enabled) {
        this.isEnabled = enabled;

        if (!isEnabled()) {
            clear();
        }

        return this;
    }

    public void clear() {
        threadRunner.clear();
    }
}
