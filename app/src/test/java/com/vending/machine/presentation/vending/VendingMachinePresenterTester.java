package com.vending.machine.presentation.vending;

import com.google.common.truth.Truth;
import com.vending.machine.app.common.concurrency.MockBackgroundThreadPool;
import com.vending.machine.app.common.data.InMemoryMultiEntityStorage;
import com.vending.machine.app.common.data.InMemorySingleObjectStorage;
import com.vending.machine.app.common.functional.Function0;
import com.vending.machine.app.common.util.MockUiThreadQueue;
import com.vending.machine.app.data.ItemInitializer;
import com.vending.machine.app.data.ItemRepository;
import com.vending.machine.app.data.MoneyRepository;
import com.vending.machine.domain.Item;
import com.vending.machine.domain.Money;
import com.vending.machine.presentation.vending.coinselection.CoinSelectionViewModel;
import com.vending.machine.presentation.vending.itemlist.ItemViewModel;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Farhan
 * 2018-05-01
 */
public class VendingMachinePresenterTester {

    private final VendingMachinePresenter presenter;
    private final VendingMachineViewModelFactory viewModelFactory;
    private final MoneyRepository moneyRepository;
    private final ItemRepository itemRepository;
    private final ItemInitializer itemInitializer;
    private final VendingMachineView view;
    private final VendingMachineViewModel viewModel;
    private final CoinSelectionViewModel coinSelectionViewModel;

    private VendingMachinePresenter.EventListener eventListener;

    VendingMachinePresenterTester() {

        viewModelFactory = mock(VendingMachineViewModelFactory.class);
        moneyRepository = new MoneyRepository(new InMemorySingleObjectStorage<>());

        itemRepository = spy(new ItemRepository(new InMemoryMultiEntityStorage<>()));
        itemRepository.saveAll(Arrays.asList(new Item("c1", "n1", Money.ZERO, 1),
                new Item("c2", "n2", Money.ZERO, 2)));

        itemInitializer = mock(ItemInitializer.class);
        view = mock(VendingMachineView.class);

        coinSelectionViewModel = mock(CoinSelectionViewModel.class);
        viewModel = mockViewModel().call();

        presenter = new VendingMachinePresenter(MockUiThreadQueue.newInstance(), MockBackgroundThreadPool.newInstance(),
                viewModelFactory, moneyRepository, itemRepository, itemInitializer);
    }

    public void viewScreen() {
        doAnswer(invocation -> {
            eventListener = invocation.getArgument(0);
            return viewModel;
        }).when(viewModelFactory).create(any());
        presenter.attach(view);
    }

    public void validateViewShown() {
        then(view).should().show(any());
    }

    public void makeItemRepositoryEmpty() {
        itemRepository.deleteAll();
    }

    public void validateItemInitializerPopulate() {
        verify(itemInitializer).populate(any());
    }

    private Function0<VendingMachineViewModel> mockViewModel() {
        return () -> {
            VendingMachineViewModel viewModel = mock(VendingMachineViewModel.class);
            when(viewModel.getCoinSelectionViewModel()).thenReturn(coinSelectionViewModel);
            when(coinSelectionViewModel.getTotal()).thenReturn(Money.ZERO);
            return viewModel;
        };
    }

    public void addCoin(Money coin) {
        when(viewModel.getSelectedCoin()).thenReturn(coin);
        eventListener.onAddClicked();
    }

    public void verifyViewShowNoCoinSelected() {
        verify(view).showNoCoinSelected();
    }

    public void setTotalCoinsTo(Money total) {
        when(coinSelectionViewModel.getTotal()).thenReturn(total);
    }

    public void verifyViewShowTotalFundsExceedAllowedLimit() {
        verify(view).showTotalFundsExceedAllowedLimit();
    }

    public void verifyViewModelUpdateTotal() {
        verify(coinSelectionViewModel).updateTotal(any());
    }

    public void verifyMoneyRepositoryValue(Money value) {
        Truth.assertThat(moneyRepository.get()).isEqualTo(value);
    }

    public void refundClicked() {
        eventListener.onRefundClicked();
    }

    public void verifyViewOnRefundClicked() {
        verify(view).onRefundClicked(any());
    }

    public void resetToDefaults() {
        eventListener.onResetToDefaults();
    }

    public void purchaseClicked(ItemViewModel itemViewModel) {
        when(viewModel.getSelectedItem()).thenReturn(itemViewModel);
        eventListener.onPurchaseClicked();
    }

    public void verifyViewShowNoItemSelected() {
        verify(view).showNoItemSelected();
    }

    public void verifyViewShowOutOfInventory(Item item) {
        verify(view).showOutOfInventory(item);
    }

    public void verifyViewShowNotEnoughFundsToPurchaseItem() {
        verify(view).showNotEnoughFundsToPurchaseItem(any(), any());
    }

    public void verifyItemSavedInRepository(Item item) {
        verify(itemRepository).save(item);
    }

    public void verifyViewShowPurchaseSuccessful(Item item) {
        verify(view).showPurchaseSuccessful(item);
    }
}
