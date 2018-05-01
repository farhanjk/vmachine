package com.vending.machine.presentation.vending.coinselection;

import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.vending.machine.BR;
import com.vending.machine.app.common.functional.Function0;
import com.vending.machine.app.common.mvp.BaseViewModel;
import com.vending.machine.app.common.mvp.viewmodel.DropDownFieldViewModel;
import com.vending.machine.domain.Money;
import com.vending.machine.domain.MoneyFormat;

/**
 * Deals with rendering and event handling of coin selection, add and refund process.
 */
public class CoinSelectionViewModel extends BaseViewModel {

    @NonNull
    private final DropDownFieldViewModel<Money> coinSelector;

    private final Function0<String> hintProvider;

    @NonNull
    private Money total = Money.ZERO;

    @NonNull
    private final Listener listener;

    public CoinSelectionViewModel(@NonNull DropDownFieldViewModel<Money> coinSelector, Function0<String> hintProvider, @NonNull Listener listener) {
        this.coinSelector = coinSelector;
        this.hintProvider = hintProvider;
        this.listener = listener;
    }

    @Bindable
    public DropDownFieldViewModel getCoinSelector() {
        return coinSelector;
    }

    public Money getCoin() {
        return coinSelector.getKey();
    }

    @Bindable
    public String getHint() {
        return hintProvider.call();
    }

    public void addClicked() {
        listener.onAddClicked();
    }

    public void refundClicked() {
        listener.onRefundClicked();
    }

    @SuppressWarnings("ConstantConditions")
    @NonNull
    @Bindable
    public String getTotalValue() {
        return MoneyFormat.formatDisplay(total);
    }

    @NonNull
    public Money getTotal() {
        return total;
    }

    public void updateTotal(@NonNull Money total) {
        this.total = total;
        notifyPropertyChanged(BR.totalValue);
    }

    public interface Listener {

        void onAddClicked();

        void onRefundClicked();
    }
}
