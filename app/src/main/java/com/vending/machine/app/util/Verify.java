package com.vending.machine.app.util;

import android.support.annotation.Nullable;

public final class Verify {
    private Verify() {
    }

    public static <T> T notNull(T reference, @Nullable Object message) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(message));
        }
        return reference;
    }

    public static <T> T notNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
