package com.vending.machine.domain;


import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MoneyTest {

    @Test
    public void verify_amounts_in_money_current_and_proposed() {

        double current = 2000;
        Money money = Money.valueOf(current);

        Assert.assertEquals(money.getValue(), 2000d);
    }

}
