package com.vending.machine.presentation.dependency;

import android.content.Context;

import com.vending.machine.presentation.MainActivity;
import com.vending.machine.presentation.vending.VendingMachineFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Dagger component for injection.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder context(Context context);

        AppComponent build();
    }

    void inject(MainActivity activity);

    void inject(VendingMachineFragment fragment);

}
