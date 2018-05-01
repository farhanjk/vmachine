package com.vending.machine.presentation.common.util;

import android.app.Activity;
import android.support.design.widget.Snackbar;

import com.vending.machine.R;

/**
 * Snackbar (success/ failure and regular) methods.
 */
public class AppSnackbar {

    public void showFailureSnackbar(Activity activity, String text) {
        if (activity == null) {
            return;
        }

        Snackbar snackbar = new SnackbarProvider.ForFailure(
                new SnackbarProvider.WithAction(
                        new SnackbarProvider.OfActivity(activity, text, Snackbar.LENGTH_LONG),
                        new SnackbarProvider.WithAction.SnackbarAction(activity.getString(R.string.ok), view -> {
                        })
                )).call();

        snackbar.show();
    }

    public void showSuccessSnackbar(Activity activity, String text) {
        if (activity == null) {
            return;
        }

        Snackbar snackbar = new SnackbarProvider.ForSuccess(
                new SnackbarProvider.WithAction(
                        new SnackbarProvider.OfActivity(activity, text, Snackbar.LENGTH_LONG),
                        new SnackbarProvider.WithAction.SnackbarAction(activity.getString(R.string.ok), view -> {
                        })
                )).call();

        snackbar.show();
    }

    public void showSnackbar(Activity activity, String text) {
        if (activity == null) {
            return;
        }

        Snackbar snackbar = new SnackbarProvider.WithAction(
                new SnackbarProvider.OfActivity(activity, text, Snackbar.LENGTH_LONG),
                new SnackbarProvider.WithAction.SnackbarAction(activity.getString(R.string.ok), view -> {
                })
        ).call();

        snackbar.show();
    }
}
