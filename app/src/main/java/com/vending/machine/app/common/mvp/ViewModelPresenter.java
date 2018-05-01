package com.vending.machine.app.common.mvp;


public interface ViewModelPresenter<V extends View, VM extends ViewModel> extends Presenter<V> {
    void attach(V view, VM viewModel);
}
