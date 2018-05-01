package com.vending.machine.presentation.vending;

import android.support.annotation.NonNull;

import com.vending.machine.app.common.mvp.View;
import com.vending.machine.app.common.mvp.ViewModelDisplayer;
import com.vending.machine.domain.Item;
import com.vending.machine.domain.Money;

/**
 * VendingMachineFragment implements this view. Abstracts Android frameworks fragment from
 * presenter and also provides a clean interface for vending machine View.
 */
public interface VendingMachineView extends View, ViewModelDisplayer<VendingMachineViewModel> {

    void showNoItemSelected();

    void onRefundClicked(@NonNull Money total);

    void showNoCoinSelected();

    void showTotalFundsExceedAllowedLimit();

    void showOutOfInventory(@NonNull Item item);

    void showPurchaseSuccessful(@NonNull Item item);

    void showNotEnoughFundsToPurchaseItem(@NonNull Item item, @NonNull Money currentTotal);

    void showResetToDefaultComplete();
}
