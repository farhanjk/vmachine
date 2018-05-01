package com.vending.machine.presentation;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vending.machine.App;
import com.vending.machine.R;
import com.vending.machine.databinding.ActivityMainBinding;
import com.vending.machine.presentation.common.navigation.Navigator;

import javax.inject.Inject;

/**
 * Main activity - responsible for loading the vending machine fragment.
 */
public class MainActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ((App) getApplication()).appComponent().inject(this);

        navigator.showVendingMachine(getSupportFragmentManager());
    }
}
