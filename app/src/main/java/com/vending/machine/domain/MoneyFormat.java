package com.vending.machine.domain;

import android.support.annotation.Nullable;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Formats Money value to $##.##
 */
public class MoneyFormat {

    private MoneyFormat() {
    }

    @Nullable
    public static String formatDisplay(@Nullable Money money) {
        if (money == null) {
            return null;
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        return formatter.format(money.getValue());
    }

}
