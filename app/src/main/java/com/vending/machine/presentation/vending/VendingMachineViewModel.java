package com.vending.machine.presentation.vending;

import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vending.machine.app.common.functional.Function0;
import com.vending.machine.app.common.mvp.BaseViewModel;
import com.vending.machine.app.common.mvp.viewmodel.DropDownFieldViewModel;
import com.vending.machine.domain.Money;
import com.vending.machine.presentation.vending.coinselection.CoinSelectionViewModel;
import com.vending.machine.presentation.vending.itemlist.ItemViewModel;
import com.vending.machine.presentation.vending.itemselection.ItemSelectionViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * ViewModel responsible for rendering and event handling of vending machine.
 */
public class VendingMachineViewModel extends BaseViewModel {

    @NonNull
    private final List<ItemViewModel> items;
    private final CoinSelectionViewModel coinSelectionViewModel;
    private final ItemSelectionViewModel itemSelectionViewModel;
    @NonNull
    private final Listener listener;

    VendingMachineViewModel(List<ItemViewModel> itemViewModelList,
                            @NonNull CoinSelectionViewModel coinSelectionViewModel,
                            @NonNull Function0<String> itemHintProvider, @NonNull Listener listener) {
        items = new ArrayList<>(itemViewModelList);
        this.coinSelectionViewModel = coinSelectionViewModel;
        this.listener = listener;

        DropDownFieldViewModel<ItemViewModel> itemSelector = new DropDownFieldViewModel<>();
        itemSelector.setMapValues(new LinkedHashMap<ItemViewModel, String>() {
            {
                put(null, itemHintProvider.call());
                for (ItemViewModel viewModel : itemViewModelList) {
                    put(viewModel, viewModel.getCode());
                }
            }
        });
        this.itemSelectionViewModel = new ItemSelectionViewModel(itemSelector, itemHintProvider, this::purchaseClicked);
    }

    @NonNull
    @Bindable
    public List<ItemViewModel> getItems() {
        return Collections.unmodifiableList(items);
    }

    @Bindable
    public CoinSelectionViewModel getCoinSelectionViewModel() {
        return coinSelectionViewModel;
    }

    @Bindable
    public ItemSelectionViewModel getItemSelectionViewModel() {
        return itemSelectionViewModel;
    }

    private void purchaseClicked() {
        listener.onPurchaseClicked();
    }

    @Nullable
    public ItemViewModel getSelectedItem() {
        return itemSelectionViewModel.getItem();
    }

    public Money getSelectedCoin() {
        return coinSelectionViewModel.getCoin();
    }

    public void resetToDefaults() {
        listener.onResetToDefaults();
    }

    public interface Listener {

        void onPurchaseClicked();

        void onAddClicked();

        void onRefundClicked();

        void onResetToDefaults();
    }
}
