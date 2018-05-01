package com.vending.machine.presentation.dependency;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.vending.machine.app.common.concurrency.BackgroundThreadPool;
import com.vending.machine.app.common.concurrency.CachedBackgroundThreadPool;
import com.vending.machine.app.common.concurrency.threadqueue.UiThreadQueue;
import com.vending.machine.app.common.data.InMemorySingleObjectStorage;
import com.vending.machine.app.data.ItemInitializer;
import com.vending.machine.app.data.ItemMultiEntityStorage;
import com.vending.machine.app.data.ItemRepository;
import com.vending.machine.app.data.MoneyRepository;
import com.vending.machine.app.room.AppDatabase;
import com.vending.machine.presentation.common.navigation.Navigator;
import com.vending.machine.presentation.common.util.AppSnackbar;
import com.vending.machine.presentation.vending.VendingMachineViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for providing dependencies.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    UiThreadQueue provideUiThreadQueue() {
        return new UiThreadQueue();
    }

    @Provides
    @Singleton
    BackgroundThreadPool provideBackgroundThreadPool() {
        return new CachedBackgroundThreadPool();
    }

    @Provides
    VendingMachineViewModelFactory provideVendingMachineViewModelFactory(Context context,
                                                                         ItemRepository itemRepository,
                                                                         MoneyRepository moneyRepository) {
        return new VendingMachineViewModelFactory(context, itemRepository, moneyRepository);
    }

    @Provides
    @Singleton
    ItemRepository provideItemRepository(AppDatabase appDatabase) {
        return new ItemRepository(new ItemMultiEntityStorage(appDatabase.itemModel()));
    }

    @Provides
    @Singleton
    ItemInitializer provideItemInitializer(ItemRepository itemRepository, BackgroundThreadPool backgroundThreadPool) {
        return new ItemInitializer(itemRepository, backgroundThreadPool);
    }

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "vending_machine")
                .build();
    }

    @Provides
    AppSnackbar provideAppSnackbar() {
        return new AppSnackbar();
    }

    @Provides
    @Singleton
    MoneyRepository provideMoneyRepository() {
        return new MoneyRepository(new InMemorySingleObjectStorage<>());
    }
}
