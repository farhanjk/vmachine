package com.vending.machine.app.common.mvp;

import com.vending.machine.app.common.concurrency.threadqueue.UiThreadQueue;

public abstract class BaseViewModelPresenter<V extends View & ViewModelDisplayer<VM>, VM extends ViewModel>
        extends BasePresenter<V> implements ViewModelPresenter<V, VM> {

    protected VM viewModel;

    public BaseViewModelPresenter(UiThreadQueue uiThreadQueue) {
        super(uiThreadQueue);
    }

    @Override
    public void attach(V view) {
        this.attach(view, null);
    }

    @Override
    public void attach(V view, VM existingViewModel) {
        super.attach(view);
        this.viewModel = existingViewModel;
    }

    @Override
    public void detach() {
        super.detach();
        viewModel = null;
    }
}
