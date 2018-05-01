package com.vending.machine.domain;

import android.arch.persistence.room.ColumnInfo;
import android.support.annotation.NonNull;

import com.vending.machine.app.common.data.Entity;

/**
 * Models the vending machine item. Also used as an Entity in Room database.
 */
@android.arch.persistence.room.Entity(tableName = "item",
        primaryKeys = {"code"})
public class Item implements Entity<String> {

    @ColumnInfo(name = "code")
    @NonNull
    private final String code;

    @ColumnInfo(name = "name")
    @NonNull
    private final String name;

    @ColumnInfo(name = "price")
    @NonNull
    private final Money price;

    @ColumnInfo(name = "inventory")
    private int inventory;

    public Item(@NonNull String code, @NonNull String name, @NonNull Money price, int inventory) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public Money getPrice() {
        return price;
    }

    public int getInventory() {
        return inventory;
    }

    public void decrementInventoryByOne() {
        this.inventory -= 1;
    }

    @Override
    public String getId() {
        return code;
    }

    @Override
    public String toString() {
        return "{" + name + "}";
    }
}
