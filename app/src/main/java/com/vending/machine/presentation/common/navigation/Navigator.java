package com.vending.machine.presentation.common.navigation;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.vending.machine.R;
import com.vending.machine.app.common.functional.Function0;
import com.vending.machine.presentation.vending.VendingMachineFragment;

/**
 * Class responsible for all navigation in the app (activity to fragment; fragment to another fragment etc).
 */
public class Navigator {

    @SuppressWarnings("FieldCanBeLocal")
    private final String TAG_VENDING_MACHINE = "vending_machine";

    private FragmentTransaction createTransaction(FragmentManager fragmentManager, Function0<Fragment> fragment, String tag, int containerId) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.screen_enter, R.anim.screen_leave, R.anim.screen_enter, R.anim.screen_leave);
        fragmentTransaction.replace(containerId, fragment.call(), tag);
        return fragmentTransaction;
    }

    public void showVendingMachine(@NonNull FragmentManager fragmentManager) {
        createTransaction(fragmentManager, VendingMachineFragment::new, TAG_VENDING_MACHINE, R.id.fragment_container)
                .commit();
    }

}
