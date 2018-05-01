package com.vending.machine.app.common.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implements an in-memory data storage that can be used to easily store a simple list of objects ex; List of companies in memory after login
 *
 * @param <O>
 */
public final class InMemoryMultiObjectStorage<O> implements MultiObjectStorage<O> {

    private List<O> list;

    public InMemoryMultiObjectStorage() {
        list = new ArrayList<>();
    }

    @Override
    public void save(O object) {
        list.add(object);
    }

    @Override
    public void delete(O object) {
        list.remove(object);
    }

    @Override
    public List<O> getAll() {
        return Collections.unmodifiableList(list);
    }

    @Override
    public void deleteAll() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

}
