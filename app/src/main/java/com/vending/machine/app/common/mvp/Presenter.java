package com.vending.machine.app.common.mvp;


public interface Presenter<V extends View> {
    void attach(V view);

    void detach();
}
