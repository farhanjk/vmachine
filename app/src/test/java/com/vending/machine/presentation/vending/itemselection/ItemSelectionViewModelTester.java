package com.vending.machine.presentation.vending.itemselection;

import com.vending.machine.app.common.functional.Function0;
import com.vending.machine.app.common.mvp.viewmodel.DropDownFieldViewModel;
import com.vending.machine.presentation.vending.itemlist.ItemViewModel;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Farhan
 * 2018-04-30
 */
public class ItemSelectionViewModelTester {

    private final ItemSelectionViewModel viewModel;
    private final DropDownFieldViewModel<ItemViewModel> itemSelector;
    private final ItemSelectionViewModel.Listener listener;
    private final Function0<String> hintProvider;

    @SuppressWarnings("unchecked")
    public ItemSelectionViewModelTester() {

        itemSelector = mock(DropDownFieldViewModel.class);
        listener = mock(ItemSelectionViewModel.Listener.class);
        hintProvider = mock(Function0.class);
        viewModel = new ItemSelectionViewModel(itemSelector, hintProvider, listener);
    }


    public void purchaseClicked() {
        viewModel.purchaseClicked();
    }

    public void verifyListenerPurchaseClicked() {
        verify(listener).onPurchaseClicked();
    }

    public void getHint() {
        viewModel.getHint();
    }

    public void verifyCorrectHintFromProvider() {
        verify(hintProvider).call();
    }
}
