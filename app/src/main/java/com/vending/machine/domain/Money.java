package com.vending.machine.domain;

import android.support.annotation.Nullable;

import com.vending.machine.app.util.Verify;

import org.apache.commons.math3.util.Precision;

/**
 * Class which abstracts Money and related functionality. Provides clean implementation of
 * plus, minus and rounding up to two decimal places.
 */
public class Money {

    @Nullable
    public static Double getValue(@Nullable Money money) {
        return money == null ? null : money.getValue();
    }

    @Nullable
    public static Integer getDollarValue(@Nullable Money money) {
        return money == null ? null : money.getDollarValue();
    }

    public static int getDollarValueOrDefault(Money amount, int defaultValue) {
        final Integer value = getDollarValue(amount);
        return value == null ? defaultValue : value;
    }

    public static Money valueOf(double value) {
        return value == 0 ? ZERO : new Money(value);
    }

    @Nullable
    public static Money valueOf(@Nullable Number value) {
        return valueOrDefault(value, null);
    }

    public static Money valueOrDefault(@Nullable Number value, @Nullable Money defaultValue) {
        return value == null ? defaultValue : valueOf(value.doubleValue());
    }

    @Nullable
    public static Money valueOf(@Nullable Integer value) {
        return value == null ? null : valueOf(value.doubleValue());
    }

    public static final Money ZERO = new Money(0);
    private static final int DECIMAL_PLACES = 2;

    private final double value;

    private Money(double value) {
        this.value = Verify.notNull(value, "Money value is null");
    }

    public double getValue() {
        return Precision.round(value, DECIMAL_PLACES);
    }

    public int getDollarValue() {
        return (int) getValue();
    }

    public double getRawValue() {
        return value;
    }

    public Money plus(Money money) {
        return Money.valueOf(value + money.value);
    }

    public Money minus(Money money) {
        return Money.valueOf(value - money.value);
    }

    public Money dividedBy(double amount) {
        return Money.valueOf(value / amount);
    }

    public Money multipliedBy(double amount) {
        return Money.valueOf(value * amount);
    }

    public boolean isLessThan(Money money) {
        return getValue() < money.getValue();
    }

    public boolean isGreaterThan(Money money) {
        return getValue() > money.getValue();
    }

    public boolean isGreaterThanOrEqualTo(Money money) {
        return getValue() >= money.getValue();
    }

    public boolean isLessThanOrEqualTo(Money money) {
        return getValue() <= money.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        return value == money.value;
    }

    @Override
    public int hashCode() {
        return (int) value;
    }

    @Override
    public String toString() {
        return Double.toString(getValue());
    }

}
