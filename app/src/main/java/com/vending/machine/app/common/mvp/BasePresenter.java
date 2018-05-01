package com.vending.machine.app.common.mvp;

import com.vending.machine.app.common.concurrency.threadqueue.UiThreadQueue;

public class BasePresenter<V extends View> implements Presenter<V> {

    protected V view;
    protected final UiThreadQueue uiThreadQueue;

    public BasePresenter(UiThreadQueue uiThreadQueue) {
        this.uiThreadQueue = uiThreadQueue;
    }

    @Override
    public void attach(V view) {
        this.view = view;
        uiThreadQueue.setEnabled(true);
    }

    @Override
    public void detach() {
        uiThreadQueue.setEnabled(false);
        view = null;
    }
}
