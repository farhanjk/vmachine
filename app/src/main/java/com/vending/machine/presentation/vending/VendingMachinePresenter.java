package com.vending.machine.presentation.vending;

import com.vending.machine.app.common.concurrency.BackgroundThreadPool;
import com.vending.machine.app.common.concurrency.threadqueue.UiThreadQueue;
import com.vending.machine.app.common.mvp.BaseViewModelPresenter;
import com.vending.machine.app.data.ItemInitializer;
import com.vending.machine.app.data.ItemRepository;
import com.vending.machine.app.data.MoneyRepository;
import com.vending.machine.domain.Item;
import com.vending.machine.domain.Money;
import com.vending.machine.presentation.vending.coinselection.CoinSelectionViewModel;
import com.vending.machine.presentation.vending.itemlist.ItemViewModel;

/**
 * Responsible for high level business modeling of the vending machine.
 * Delegates low level tasks such as data storage and handling to dependencies.
 */
public class VendingMachinePresenter extends BaseViewModelPresenter<VendingMachineView, VendingMachineViewModel> {

    private final BackgroundThreadPool backgroundThreadPool;
    private final VendingMachineViewModelFactory viewModelFactory;
    private final MoneyRepository moneyRepository;
    private final ItemRepository itemRepository;
    private final ItemInitializer itemInitializer;

    VendingMachinePresenter(UiThreadQueue uiThreadQueue,
                            BackgroundThreadPool backgroundThreadPool,
                            VendingMachineViewModelFactory viewModelFactory,
                            MoneyRepository moneyRepository,
                            ItemRepository itemRepository, ItemInitializer itemInitializer) {
        super(uiThreadQueue);
        this.backgroundThreadPool = backgroundThreadPool;
        this.viewModelFactory = viewModelFactory;
        this.moneyRepository = moneyRepository;
        this.itemRepository = itemRepository;
        this.itemInitializer = itemInitializer;
    }

    @Override
    public void attach(VendingMachineView view) {
        backgroundThreadPool.run(() -> {
            if (itemRepository.getAll().isEmpty()) {
                itemInitializer.populate(() -> showView(view));
            } else {
                showView(view);
            }
        });
    }

    private void showView(VendingMachineView view) {
        this.viewModel = viewModelFactory.create(new EventListener());
        super.attach(view, this.viewModel);

        uiThreadQueue.run(() -> view.show(viewModel));
    }

    class EventListener implements VendingMachineViewModel.Listener {

        @Override
        public void onPurchaseClicked() {
            backgroundThreadPool.run(() -> {
                ItemViewModel selectedItem = viewModel.getSelectedItem();
                if (selectedItem == null) {
                    uiThreadQueue.run(() -> view.showNoItemSelected());
                } else {
                    Item item = selectedItem.getItem();
                    if (item.getInventory() <= 0) {
                        uiThreadQueue.run(() -> view.showOutOfInventory(item));
                    } else {
                        makePurchase(selectedItem, item);
                    }
                }
            });
        }

        private void makePurchase(ItemViewModel selectedItem, Item item) {
            CoinSelectionViewModel coinSelectionViewModel = viewModel.getCoinSelectionViewModel();
            Money currentTotal = coinSelectionViewModel.getTotal();
            Money newTotal = currentTotal.minus(item.getPrice());

            if (newTotal.isLessThan(Money.ZERO)) {
                coinSelectionViewModel.updateTotal(Money.ZERO);
                moneyRepository.save(Money.ZERO);

                uiThreadQueue.run(() -> view.showNotEnoughFundsToPurchaseItem(item, currentTotal));
            } else {
                coinSelectionViewModel.updateTotal(newTotal);
                moneyRepository.save(newTotal);

                item.decrementInventoryByOne();
                itemRepository.save(item);
                selectedItem.notifyChange();

                uiThreadQueue.run(() -> view.showPurchaseSuccessful(item));
            }
        }

        @Override
        public void onAddClicked() {
            backgroundThreadPool.run(() -> {
                if (viewModel.getSelectedCoin() == null) {
                    uiThreadQueue.run(() -> view.showNoCoinSelected());
                } else {
                    Money total = viewModel.getCoinSelectionViewModel().getTotal().plus(viewModel.getSelectedCoin());
                    if (total.isGreaterThan(Money.valueOf(1.0))) {
                        uiThreadQueue.run(() -> view.showTotalFundsExceedAllowedLimit());
                    } else {
                        moneyRepository.save(total);
                        uiThreadQueue.run(() -> viewModel.getCoinSelectionViewModel().updateTotal(total));
                    }
                }
            });
        }

        @Override
        public void onRefundClicked() {
            backgroundThreadPool.run(() -> {
                CoinSelectionViewModel coinSelectionViewModel = viewModel.getCoinSelectionViewModel();
                Money total = coinSelectionViewModel.getTotal();
                moneyRepository.save(Money.ZERO);
                coinSelectionViewModel.updateTotal(Money.ZERO);
                uiThreadQueue.run(() -> view.onRefundClicked(total));
            });
        }

        @Override
        public void onResetToDefaults() {
            backgroundThreadPool.run(() -> itemInitializer.populate(() -> {
                attach(view);
                view.showResetToDefaultComplete();
            }));
        }
    }
}
