package com.vending.machine.presentation.vending;

import android.content.Context;

import com.vending.machine.R;
import com.vending.machine.app.common.mvp.viewmodel.DropDownFieldViewModel;
import com.vending.machine.app.data.ItemRepository;
import com.vending.machine.app.data.MoneyRepository;
import com.vending.machine.domain.Item;
import com.vending.machine.domain.Money;
import com.vending.machine.presentation.vending.coinselection.CoinSelectionViewModel;
import com.vending.machine.presentation.vending.itemlist.ItemViewModel;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Builds the vending machine view model.
 * The benefit in this case is that Android specific Context class is abstracted out of the viewModel
 * which is good for testing.
 */
public class VendingMachineViewModelFactory {

    private final Context context;
    private final ItemRepository itemRepository;
    private final MoneyRepository moneyRepository;

    public VendingMachineViewModelFactory(Context context,
                                          ItemRepository itemRepository,
                                          MoneyRepository moneyRepository) {
        this.context = context;
        this.itemRepository = itemRepository;
        this.moneyRepository = moneyRepository;
    }

    VendingMachineViewModel create(VendingMachineViewModel.Listener listener) {
        DropDownFieldViewModel<Money> coinSelector = new DropDownFieldViewModel<>();
        coinSelector.setMapValues(new LinkedHashMap<Money, String>() {
            {
                put(null, context.getString(R.string.v_m_select_coin));

                put(Money.valueOf(0.05), "5c");
                put(Money.valueOf(0.10), "10c");
                put(Money.valueOf(0.25), "25c");
            }
        });
        CoinSelectionViewModel coinSelectionViewModel = new CoinSelectionViewModel(coinSelector, () -> context.getString(R.string.v_m_select_coin), new CoinSelectionViewModel.Listener() {
            @Override
            public void onAddClicked() {
                listener.onAddClicked();
            }

            @Override
            public void onRefundClicked() {
                listener.onRefundClicked();
            }
        });
        Money money = moneyRepository.get();
        coinSelectionViewModel.updateTotal(money == null ? Money.ZERO : money);

        List<ItemViewModel> itemViewModelList = new LinkedList<ItemViewModel>() {
            {
                for (Item item : itemRepository.getAll()) {
                    add(new ItemViewModel(item));
                }
            }
        };
        return new VendingMachineViewModel(itemViewModelList, coinSelectionViewModel, () -> context.getString(R.string.v_m_select_item), listener);
    }
}
