package com.vending.machine.domain;

import com.google.common.truth.Truth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Farhan
 * 2018-04-30
 */
@RunWith(JUnit4.class)
public class ItemTest {

    @Test
    public void given_an_item_code_it_is_returned_as_entity_id() {
        Item item = new Item("code1", "item1", Money.ZERO, 1);

        Truth.assertThat(item.getCode()).isEqualTo("code1");
        Truth.assertThat(item.getId()).isEqualTo("code1");
    }

    @Test
    public void given_inventory_decremented_by_one_returns_correct_value() {
        Item item = new Item("code1", "item1", Money.ZERO, 1);
        item.decrementInventoryByOne();

        Truth.assertThat(item.getInventory()).isEqualTo(0);
    }
}
