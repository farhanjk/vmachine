package com.vending.machine.presentation.vending.itemselection;

import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.vending.machine.app.common.functional.Function0;
import com.vending.machine.app.common.mvp.BaseViewModel;
import com.vending.machine.app.common.mvp.viewmodel.DropDownFieldViewModel;
import com.vending.machine.presentation.vending.itemlist.ItemViewModel;

/**
 * ViewModel responsible for view updates and events for selecting and purchasing an item.
 */
public class ItemSelectionViewModel extends BaseViewModel {

    @NonNull
    private final DropDownFieldViewModel<ItemViewModel> itemSelector;

    private final Function0<String> hintProvider;

    @NonNull
    private final Listener listener;

    public ItemSelectionViewModel(@NonNull DropDownFieldViewModel<ItemViewModel> itemSelector,
                                  Function0<String> hintProvider, @NonNull Listener listener) {
        this.itemSelector = itemSelector;
        this.hintProvider = hintProvider;
        this.listener = listener;
    }

    @Bindable
    public DropDownFieldViewModel getItemSelector() {
        return itemSelector;
    }

    public ItemViewModel getItem() {
        return itemSelector.getKey();
    }

    @Bindable
    public String getHint() {
        return hintProvider.call();
    }

    public void purchaseClicked() {
        listener.onPurchaseClicked();
    }

    public interface Listener {

        void onPurchaseClicked();
    }
}
