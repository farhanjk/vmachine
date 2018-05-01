package com.vending.machine.presentation.vending.itemselection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Farhan
 * 2018-04-30
 */
@RunWith(JUnit4.class)
public class ItemSelectonViewModelTest {

    @Test
    public void given_purchaseClicked_informs_listener() {
        ItemSelectionViewModelTester tester = new ItemSelectionViewModelTester();
        tester.purchaseClicked();
        tester.verifyListenerPurchaseClicked();
    }

    @Test
    public void given_asked_for_hint_returns_correct_value() {
        ItemSelectionViewModelTester tester = new ItemSelectionViewModelTester();
        tester.getHint();
        tester.verifyCorrectHintFromProvider();
    }
}
