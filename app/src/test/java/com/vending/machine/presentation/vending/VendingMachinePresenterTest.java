package com.vending.machine.presentation.vending;

import com.google.common.truth.Truth;
import com.vending.machine.domain.Item;
import com.vending.machine.domain.Money;
import com.vending.machine.presentation.vending.itemlist.ItemViewModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Farhan
 * 2018-05-01
 */
@RunWith(JUnit4.class)
public class VendingMachinePresenterTest {

    @Test
    public void given_presenter_is_attached_it_and_item_repo_is_not_empty_it_should_display_view_model() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();

        tester.validateViewShown();
    }

    @Test
    public void given_presenter_is_attached_and_item_repo_is_empty_it_populates_data() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.makeItemRepositoryEmpty();
        tester.viewScreen();

        tester.validateItemInitializerPopulate();
    }

    @Test
    public void given_add_coin_and_no_coin_selected_informs_view() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        tester.addCoin(null);

        tester.verifyViewShowNoCoinSelected();
    }

    @Test
    public void given_add_coin_and_total_greater_than_one_dollar_informs_view() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        tester.setTotalCoinsTo(Money.valueOf(0.80));
        tester.addCoin(Money.valueOf(0.25));

        tester.verifyViewShowTotalFundsExceedAllowedLimit();
    }

    @Test
    public void given_add_coin_and_total_updated_informs_view_model() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        tester.setTotalCoinsTo(Money.valueOf(0.50));
        tester.addCoin(Money.valueOf(0.25));

        tester.verifyViewModelUpdateTotal();
    }

    @Test
    public void given_add_coin_and_total_updated_repository_reflects_correct_total() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        tester.setTotalCoinsTo(Money.valueOf(0.50));
        tester.addCoin(Money.valueOf(0.25));

        tester.verifyMoneyRepositoryValue(Money.valueOf(0.75));
    }

    @Test
    public void given_refund_requested_repository_reflects_zero_total() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        tester.refundClicked();

        tester.verifyMoneyRepositoryValue(Money.ZERO);
    }

    @Test
    public void given_refund_requested_informs_view() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        tester.refundClicked();

        tester.verifyViewOnRefundClicked();
    }

    @Test
    public void given_reset_to_default_inventory_requested_calls_populate_items() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        tester.resetToDefaults();

        tester.validateItemInitializerPopulate();
    }

    @Test
    public void given_purchase_clicked_and_no_item_selected_informs_view() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        tester.purchaseClicked(null);

        tester.verifyViewShowNoItemSelected();
    }

    @Test
    public void given_purchase_clicked_and_item_has_zero_invebtory_informs_view() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        Item item = new Item("c1", "n1", Money.ZERO, 0);
        tester.purchaseClicked(new ItemViewModel(item));

        tester.verifyViewShowOutOfInventory(item);
    }

    @Test
    public void given_purchase_clicked_and_item_is_valid_but_insufficient_funds_refunds_amount() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        tester.setTotalCoinsTo(Money.valueOf(0.50));
        Item item = new Item("c1", "n1", Money.valueOf(0.55), 2);
        tester.purchaseClicked(new ItemViewModel(item));

        tester.verifyMoneyRepositoryValue(Money.ZERO);
        tester.verifyViewModelUpdateTotal();
    }

    @Test
    public void given_purchase_clicked_and_item_is_valid_but_insufficient_funds_informs_view() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        tester.setTotalCoinsTo(Money.valueOf(0.50));
        Item item = new Item("c1", "n1", Money.valueOf(0.55), 2);
        tester.purchaseClicked(new ItemViewModel(item));

        tester.verifyViewShowNotEnoughFundsToPurchaseItem();
    }

    @Test
    public void given_purchase_clicked_and_item_is_valid_subtracts_total_from_user_funds() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        tester.setTotalCoinsTo(Money.valueOf(0.75));
        Item item = new Item("c1", "n1", Money.valueOf(0.50), 2);
        tester.purchaseClicked(new ItemViewModel(item));

        tester.verifyMoneyRepositoryValue(Money.valueOf(0.25));
        tester.verifyViewModelUpdateTotal();
    }

    @Test
    public void given_purchase_clicked_and_item_is_valid_decrements_inventory_by_one() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        tester.setTotalCoinsTo(Money.valueOf(0.75));
        Item item = new Item("c1", "n1", Money.valueOf(0.50), 2);
        tester.purchaseClicked(new ItemViewModel(item));

        Truth.assertThat(item.getInventory()).isEqualTo(1);
    }

    @Test
    public void given_purchase_clicked_and_item_is_valid_updates_item_in_repository() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        tester.setTotalCoinsTo(Money.valueOf(0.75));
        Item item = new Item("c1", "n1", Money.valueOf(0.50), 2);
        tester.purchaseClicked(new ItemViewModel(item));

        tester.verifyItemSavedInRepository(item);
    }

    @Test
    public void given_purchase_clicked_and_item_is_valid_informs_view() {
        VendingMachinePresenterTester tester = new VendingMachinePresenterTester();
        tester.viewScreen();
        tester.setTotalCoinsTo(Money.valueOf(0.75));
        Item item = new Item("c1", "n1", Money.valueOf(0.50), 2);
        tester.purchaseClicked(new ItemViewModel(item));

        tester.verifyViewShowPurchaseSuccessful(item);
    }

}
