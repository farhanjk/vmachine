package com.vending.machine.presentation.vending;

import com.google.common.truth.Truth;
import com.vending.machine.domain.Item;
import com.vending.machine.domain.Money;
import com.vending.machine.presentation.vending.coinselection.CoinSelectionViewModel;
import com.vending.machine.presentation.vending.itemlist.ItemViewModel;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Farhan
 * 2018-04-30
 */
public class VendingMachineViewModelTester {

    private final VendingMachineViewModel viewModel;
    private final List<ItemViewModel> itemViewModelList;
    private final CoinSelectionViewModel coinSelectionViewModel;
    private final VendingMachineViewModel.Listener listener;

    VendingMachineViewModelTester() {

        //noinspection unchecked
        itemViewModelList = Arrays.asList(
                new ItemViewModel(new Item("c1", "n1", Money.ZERO, 1)),
                new ItemViewModel(new Item("c2", "n2", Money.ZERO, 2)));


        coinSelectionViewModel = mock(CoinSelectionViewModel.class);

        listener = mock(VendingMachineViewModel.Listener.class);

        viewModel = new VendingMachineViewModel(itemViewModelList, coinSelectionViewModel, () -> "hint", listener);
    }

    public void verifyItemSelectionViewModelsCreated() {
        Map map = viewModel.getItemSelectionViewModel().getItemSelector().getMapValues();
        Truth.assertThat(map.size() - 1).isEqualTo(itemViewModelList.size());

        Iterator it = map.entrySet().iterator();
        Iterator it2 = itemViewModelList.iterator();
        it.next();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Truth.assertThat(pair.getKey()).isEqualTo(it2.next());
        }
    }

    public void purchaseClickedOnItemSelectionViewModel() {
        viewModel.getItemSelectionViewModel().purchaseClicked();
    }

    public void verifyListenerOnPurchaseClicked() {
        verify(listener).onPurchaseClicked();
    }

    public void verifyListenerOnAddClicked() {
        verify(listener).onAddClicked();
    }

    public void addClickedOnPassedCoinSelectionViewModel() {
        doAnswer(invocation -> {
            listener.onAddClicked();
            return null;
        }).when(coinSelectionViewModel).addClicked();
        coinSelectionViewModel.addClicked();
    }

    public void refundClickedOnPassedCoinSelectionViewModel() {
        doAnswer(invocation -> {
            listener.onRefundClicked();
            return null;
        }).when(coinSelectionViewModel).refundClicked();
        coinSelectionViewModel.refundClicked();
    }

    public void verifyListenerOnRefundClicked() {
        verify(listener).onRefundClicked();
    }

    public void resetToDefaults() {
        viewModel.resetToDefaults();
    }

    public void verifyListenerOnResetToDefaults() {
        verify(listener).onResetToDefaults();
    }

    public void verifyGetsCoinFromPassedCoinSelectionViewModel() {
        verify(coinSelectionViewModel).getCoin();
    }

    public void getSelectedCoin() {
        viewModel.getSelectedCoin();
    }
}
