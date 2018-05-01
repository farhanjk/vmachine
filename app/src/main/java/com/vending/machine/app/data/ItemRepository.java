package com.vending.machine.app.data;

import com.vending.machine.app.common.data.MultiEntityStorage;
import com.vending.machine.domain.Item;

import java.util.Collection;
import java.util.List;

/**
 * Saves the list of items.
 */
public class ItemRepository {

    private final MultiEntityStorage<Item, String> storage;

    public ItemRepository(MultiEntityStorage<Item, String> storage) {
        this.storage = storage;
    }

    public void saveAll(Collection<Item> itemCollection) {
        for (Item item : itemCollection) {
            save(item);
        }
    }

    public void save(final Item item) {
        storage.save(item);
    }

    public Item getById(String itemId) {
        return storage.getById(itemId);
    }

    public List<Item> getAll() {
        return storage.getAll();
    }

    public int size() {
        return storage.size();
    }

    public void delete(Item item) {
        storage.delete(item);
    }

    public void deleteAll() {
        storage.deleteAll();
    }

}