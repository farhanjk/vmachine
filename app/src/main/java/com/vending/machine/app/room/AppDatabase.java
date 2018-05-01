package com.vending.machine.app.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.vending.machine.domain.Item;
import com.vending.machine.domain.ItemDao;

@Database(entities = {Item.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract ItemDao itemModel();

}