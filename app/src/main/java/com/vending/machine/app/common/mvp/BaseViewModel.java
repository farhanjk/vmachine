package com.vending.machine.app.common.mvp;

import android.databinding.BaseObservable;
import android.databinding.Observable;

import com.vending.machine.app.common.functional.Function0;


public class BaseViewModel extends BaseObservable implements ViewModel {
    private static final long serialVersionUID = 5646936201945592318L;

    protected <T extends BaseObservable> T createField(Function0<T> fieldFactory) {
        T field = fieldFactory.call();
        field.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                BaseViewModel.this.notifyChange();
            }
        });
        return field;
    }
}
