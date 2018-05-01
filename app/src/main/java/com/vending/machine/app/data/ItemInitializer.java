package com.vending.machine.app.data;

import com.vending.machine.app.common.concurrency.BackgroundThreadPool;
import com.vending.machine.domain.Item;
import com.vending.machine.domain.Money;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Populates item/ inventory data when app starts for the first time.
 * Also, used when user requests `reset to default inventory`.
 */
public class ItemInitializer {

    private final ItemRepository itemRepository;
    private final BackgroundThreadPool backgroundThreadPool;

    public ItemInitializer(ItemRepository itemRepository, BackgroundThreadPool backgroundThreadPool) {
        this.itemRepository = itemRepository;
        this.backgroundThreadPool = backgroundThreadPool;
    }

    public void populate(Runnable onDone) {
        backgroundThreadPool.run(() ->
                itemRepository.saveAll(new ArrayList<Item>() {
                    {
                        add(new Item("It01", "Item1", Money.valueOf(0.55), 10));
                        add(new Item("It02", "Item2", Money.valueOf(0.70), 10));
                        add(new Item("It03", "Item3", Money.valueOf(0.75), 10));

                    }
                }));
        // temporary workaround for insert data thread to complete before continuining.
        try {
            Thread.sleep(100);
            onDone.run();
        } catch (InterruptedException e) {
            Timber.d("Thread for insertion interrupted.");
        }
    }
}
