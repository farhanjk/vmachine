package com.vending.machine;

import android.app.Application;

import com.vending.machine.presentation.dependency.AppComponent;
import com.vending.machine.presentation.dependency.DaggerAppComponent;

import timber.log.Timber;

/**
 * App - Resposible for Dagger2 component initialization.
 */
public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        appComponent = DaggerAppComponent.builder().context(this).build();
    }

    public AppComponent appComponent() {
        return appComponent;
    }
}
