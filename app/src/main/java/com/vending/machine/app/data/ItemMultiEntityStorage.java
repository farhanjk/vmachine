package com.vending.machine.app.data;

import com.vending.machine.app.common.data.MultiEntityStorage;
import com.vending.machine.domain.Item;
import com.vending.machine.domain.ItemDao;

import java.util.List;

/**
 * Wrapper around ItemDao. Useful in testing where we can replace the database storage with
 * in memory storage.
 */
public class ItemMultiEntityStorage implements MultiEntityStorage<Item, String> {

    private final ItemDao dao;

    public ItemMultiEntityStorage(ItemDao dao) {
        this.dao = dao;
    }

    @Override
    public Item getById(String id) {
        return dao.getItem(id);
    }

    @Override
    public void deleteById(String id) {
        dao.deleteById(id);
    }

    @Override
    public void save(Item object) {
        dao.insert(object);
    }

    @Override
    public List<Item> getAll() {
        return dao.getAll();
    }

    @Override
    public void delete(Item object) {
        dao.delete(object);
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public int size() {
        return dao.size();
    }
}
