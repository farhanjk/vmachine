package com.vending.machine.presentation.common.util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.vending.machine.R;
import com.vending.machine.app.common.functional.Function0;

abstract class SnackbarProvider implements Function0<Snackbar> {

    /**
     * Provides Snackbar attached to main view of activity.
     */
    public static class OfActivity implements Function0<Snackbar> {

        @NonNull
        private final Activity activity;

        @NonNull
        private final CharSequence text;

        @Snackbar.Duration
        private final int duration;

        OfActivity(@NonNull Activity activity, @NonNull CharSequence text, int duration) {
            this.activity = activity;
            this.text = text;
            this.duration = duration;
        }

        @Override
        public Snackbar call() {
            return Snackbar.make(activity.findViewById(android.R.id.content), text, duration);
        }

    }

    /**
     * Decorator which attaches action to Snackbar.
     */
    public static class WithAction implements Function0<Snackbar> {

        @NonNull
        private final Function0<Snackbar> snackbar;

        @NonNull
        private final Action action;

        WithAction(@NonNull Function0<Snackbar> snackbar, @NonNull Action action) {
            this.snackbar = snackbar;
            this.action = action;
        }

        @Override
        public Snackbar call() {
            Snackbar snackbar = this.snackbar.call();
            snackbar.setActionTextColor(ContextCompat.getColor(snackbar.getContext(), R.color.white));
            snackbar.setAction(action.actionText(), action.responseListener());
            return snackbar;
        }

        public interface Action {
            CharSequence actionText();

            View.OnClickListener responseListener();
        }

        public static class SnackbarAction implements Action {

            @NonNull
            private final CharSequence actionText;

            @NonNull
            private final View.OnClickListener responseListener;

            SnackbarAction(@NonNull CharSequence actionText, @NonNull View.OnClickListener responseListener) {
                this.actionText = actionText;
                this.responseListener = responseListener;
            }


            @Override
            public CharSequence actionText() {
                return actionText;
            }

            @Override
            public View.OnClickListener responseListener() {
                return responseListener;
            }
        }
    }

    /**
     * Decorator which changes color of Snackbar background and action text.
     */
    public static class ForFailure implements Function0<Snackbar> {

        @NonNull
        private final Function0<Snackbar> snackbar;

        ForFailure(@NonNull Function0<Snackbar> snackbar) {
            this.snackbar = snackbar;
        }

        @Override
        public Snackbar call() {
            Snackbar snackbar = this.snackbar.call();
            snackbar.setActionTextColor(ContextCompat.getColor(snackbar.getContext(), R.color.white));
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(snackbar.getContext(), R.color.red));
            return snackbar;
        }
    }

    /**
     * Decorator which changes color of Snackbar background and action text.
     */
    public static class ForSuccess implements Function0<Snackbar> {

        @NonNull
        private final Function0<Snackbar> snackbar;

        ForSuccess(@NonNull Function0<Snackbar> snackbar) {
            this.snackbar = snackbar;
        }

        @Override
        public Snackbar call() {
            Snackbar snackbar = this.snackbar.call();
            snackbar.setActionTextColor(ContextCompat.getColor(snackbar.getContext(), R.color.white));
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(snackbar.getContext(), R.color.shamrock));
            return snackbar;
        }
    }

}
