package com.vending.machine.domain;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MoneyFormatTest {

    @Test
    public void given_a_one_digit_double_result_should_be_single_dollar_value() {
        assertExpectedMoneyFormatDisplayString(1.34, "$1.34");
    }

    @Test
    public void given_a_double_digit_double_result_should_be_ten_dollar_value() {
        assertExpectedMoneyFormatDisplayString(12.6, "$12.60");
    }

    @Test
    public void given_zero_dollar_the_result_should_be_zero_dollar_value() {
        assertExpectedMoneyFormatDisplayString(0.7, "$0.70");
    }

    private void assertExpectedMoneyFormatDisplayString(double amount, String expected) {
        Money amountMoney = Money.valueOf(amount);
        Assert.assertEquals(expected, MoneyFormat.formatDisplay(amountMoney));
    }

}
