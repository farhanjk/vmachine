package com.vending.machine.presentation.vending;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Farhan
 * 2018-04-30
 */
@RunWith(JUnit4.class)
public class VendingMachineViewModelTest {

    @Test
    public void given_view_model_created_it_is_creates_view_models_for_passed_items() {
        VendingMachineViewModelTester tester = new VendingMachineViewModelTester();

        tester.verifyItemSelectionViewModelsCreated();
    }

    @Test
    public void given_purchaseClicked_called_on_item_selection_view_model_informs_listener() {
        VendingMachineViewModelTester tester = new VendingMachineViewModelTester();
        tester.purchaseClickedOnItemSelectionViewModel();

        tester.verifyListenerOnPurchaseClicked();
    }

    @Test
    public void given_addClicked_called_on_passed_coin_selection_view_model_informs_listener() {
        VendingMachineViewModelTester tester = new VendingMachineViewModelTester();
        tester.addClickedOnPassedCoinSelectionViewModel();

        tester.verifyListenerOnAddClicked();
    }

    @Test
    public void given_refundClicked_called_on_passed_coin_selection_view_model_informs_listener() {
        VendingMachineViewModelTester tester = new VendingMachineViewModelTester();
        tester.refundClickedOnPassedCoinSelectionViewModel();

        tester.verifyListenerOnRefundClicked();
    }

    @Test
    public void given_reset_to_defaults_requested_informs_listener() {
        VendingMachineViewModelTester tester = new VendingMachineViewModelTester();
        tester.resetToDefaults();

        tester.verifyListenerOnResetToDefaults();
    }

    @Test
    public void given_asked_for_selected_coin_gets_it_from_passed_coin_selection_view_model() {
        VendingMachineViewModelTester tester = new VendingMachineViewModelTester();
        tester.getSelectedCoin();

        tester.verifyGetsCoinFromPassedCoinSelectionViewModel();
    }
}
