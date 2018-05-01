package com.vending.machine.app.common.mvp;


public interface ViewModelDisplayer<VM extends ViewModel> {
    void show(VM viewModel);
}
