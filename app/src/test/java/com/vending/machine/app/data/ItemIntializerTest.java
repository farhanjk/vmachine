package com.vending.machine.app.data;

import com.vending.machine.app.common.concurrency.BackgroundThreadPool;
import com.vending.machine.app.common.concurrency.MockBackgroundThreadPool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Farhan
 * 2018-04-30
 */
@RunWith(JUnit4.class)
public class ItemIntializerTest {

    @Test
    public void given_populate_is_called_it_saves_items_in_item_repository() {
        ItemRepository itemRepository = mock(ItemRepository.class);
        BackgroundThreadPool backgroundThreadPool = MockBackgroundThreadPool.newInstance();
        ItemInitializer itemInitializer = new ItemInitializer(itemRepository, backgroundThreadPool);

        itemInitializer.populate(() -> {
        });

        verify(itemRepository).saveAll(anyCollection());
    }

    @Test
    public void given_populate_is_called_it_executes_the_passed_runnable() {
        ItemRepository itemRepository = mock(ItemRepository.class);
        BackgroundThreadPool backgroundThreadPool = MockBackgroundThreadPool.newInstance();
        ItemInitializer itemInitializer = new ItemInitializer(itemRepository, backgroundThreadPool);

        Runnable runnable = mock(Runnable.class);
        itemInitializer.populate(runnable);

        verify(runnable).run();
    }
}
