package com.vending.machine.app.common.mvp.viewmodel;

import android.databinding.Bindable;

import com.vending.machine.BR;
import com.vending.machine.app.common.mvp.BaseViewModel;

public class InputFieldViewModel extends BaseViewModel {

    private String value;

    @Bindable
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;

        notifyPropertyChanged(BR.value);
    }

}
