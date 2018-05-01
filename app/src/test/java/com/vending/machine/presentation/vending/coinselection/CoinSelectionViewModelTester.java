package com.vending.machine.presentation.vending.coinselection;

import com.google.common.truth.Truth;
import com.vending.machine.app.common.mvp.viewmodel.DropDownFieldViewModel;
import com.vending.machine.domain.Money;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Farhan
 * 2018-04-30
 */
public class CoinSelectionViewModelTester {

    private final CoinSelectionViewModel viewModel;
    private final DropDownFieldViewModel<Money> coinSelector;
    private final CoinSelectionViewModel.Listener listener;

    @SuppressWarnings("unchecked")
    CoinSelectionViewModelTester() {

        this.coinSelector = mock(DropDownFieldViewModel.class);
        this.listener = mock(CoinSelectionViewModel.Listener.class);

        this.viewModel = new CoinSelectionViewModel(coinSelector, () -> "hint", listener);
    }

    public void addClicked() {
        viewModel.addClicked();
    }

    public void verifyListenerOnAddClicked() {
        verify(listener).onAddClicked();
    }

    public void refundClicked() {
        viewModel.refundClicked();
    }

    public void verifyListenerOnRefundClicked() {
        verify(listener).onRefundClicked();
    }

    public void verifyTotalValueIs(String value) {
        Truth.assertThat(viewModel.getTotalValue()).isEqualTo(value);
    }

    public void verifyTotalIs(Money money) {
        Truth.assertThat(viewModel.getTotal()).isEqualTo(money);
    }

    public void updateTotal(Money money) {
        viewModel.updateTotal(money);
    }
}
