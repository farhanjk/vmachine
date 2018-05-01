package com.vending.machine.presentation.vending.itemlist;

import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.vending.machine.app.common.mvp.BaseViewModel;
import com.vending.machine.domain.Item;
import com.vending.machine.domain.MoneyFormat;

/**
 * Responsible for rendering the item in RecyclerView and Spinner.
 */
public class ItemViewModel extends BaseViewModel {

    @NonNull
    private final Item item;

    public ItemViewModel(@NonNull Item item) {
        this.item = item;
    }

    @NonNull
    @Bindable
    public String getCode() {
        return item.getCode();
    }

    @NonNull
    @Bindable
    public String getName() {
        return item.getName();
    }

    @Bindable
    public String getPrice() {
        return MoneyFormat.formatDisplay(item.getPrice());
    }

    @Bindable
    public String getInventory() {
        return String.valueOf(item.getInventory());
    }

    @NonNull
    public Item getItem() {
        return item;
    }
}
