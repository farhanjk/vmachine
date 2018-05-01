package com.vending.machine.app.data;

import android.support.annotation.Nullable;

import com.vending.machine.app.common.data.SingleObjectStorage;
import com.vending.machine.domain.Money;

/**
 * Keeps track of the running total of funds entered in the machine by user.
 */
public class MoneyRepository {

    private SingleObjectStorage<Money> storage;
    private Money money;

    public MoneyRepository(SingleObjectStorage<Money> storage) {
        this.storage = storage;
    }

    public void save(@Nullable Money money) {
        this.money = money;
        storage.save(money);
    }

    public void delete() {
        this.money = null;
        storage.delete();
    }

    @Nullable
    public synchronized Money get() {
        if (money == null) {
            money = storage.get();
        }
        return money;
    }


}
