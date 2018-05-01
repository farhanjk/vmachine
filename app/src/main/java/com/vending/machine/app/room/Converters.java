package com.vending.machine.app.room;

import android.arch.persistence.room.TypeConverter;

import com.vending.machine.domain.Money;

/**
 * Farhan
 * 2018-04-30
 */
public class Converters {

    @TypeConverter
    public static Money fromValue(double value) {
        return Money.valueOf(value);
    }

    @TypeConverter
    public static double moneyToNumber(Money money) {
        return money == null ? 0 : money.getValue();
    }

}
