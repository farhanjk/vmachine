package com.vending.machine.presentation.vending.coinselection;

import com.vending.machine.domain.Money;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Farhan
 * 2018-04-30
 */
@RunWith(JUnit4.class)
public class CoinSelectionViewModelTest {

    @Test
    public void given_addClicked_event_informs_listener() {
        CoinSelectionViewModelTester tester = new CoinSelectionViewModelTester();
        tester.addClicked();

        tester.verifyListenerOnAddClicked();
    }

    @Test
    public void given_refundClicked_event_informs_listener() {
        CoinSelectionViewModelTester tester = new CoinSelectionViewModelTester();
        tester.refundClicked();

        tester.verifyListenerOnRefundClicked();
    }

    @Test
    public void given_instantiated_total_value_is_zero_dollars() {
        CoinSelectionViewModelTester tester = new CoinSelectionViewModelTester();

        tester.verifyTotalValueIs("$0.00");
    }

    @Test
    public void given_instantiated_total_is_zero_money() {
        CoinSelectionViewModelTester tester = new CoinSelectionViewModelTester();

        tester.verifyTotalIs(Money.ZERO);
    }

    @Test
    public void given_request_to_update_total_updates_it() {
        CoinSelectionViewModelTester tester = new CoinSelectionViewModelTester();
        tester.updateTotal(Money.valueOf(7.65));

        tester.verifyTotalIs(Money.valueOf(7.65));
    }
}
