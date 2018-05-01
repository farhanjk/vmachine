package com.vending.machine.presentation.vending.itemlist;

import com.google.common.truth.Truth;
import com.vending.machine.domain.Item;
import com.vending.machine.domain.Money;
import com.vending.machine.domain.MoneyFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Farhan
 * 2018-04-30
 */
@RunWith(JUnit4.class)
public class ItemViewModelTest {

    @Test
    public void given_item_maps_it_correctly() {
        Item item = new Item("c1", "n1", Money.ZERO, 50);

        ItemViewModel itemViewModel = new ItemViewModel(item);
        Truth.assertThat(itemViewModel.getItem()).isEqualTo(item);
        Truth.assertThat(itemViewModel.getCode()).isEqualTo(item.getCode());
        Truth.assertThat(itemViewModel.getName()).isEqualTo(item.getName());
        Truth.assertThat(itemViewModel.getInventory()).isEqualTo(String.valueOf(item.getInventory()));
        Truth.assertThat(itemViewModel.getPrice()).isEqualTo(MoneyFormat.formatDisplay(item.getPrice()));
    }
}
