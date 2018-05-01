package com.vending.machine.app.common.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements an inmemory data storage that can be used to easily store a simple list of entities ex; List of loans in memory after login
 *
 * @param <E>
 */
public class InMemoryMultiEntityStorage<E extends Entity<ID>, ID> implements MultiEntityStorage<E, ID> {

    private Map<ID, E> entityMap;

    public InMemoryMultiEntityStorage() {
        this.entityMap = new LinkedHashMap<>();
    }

    @Override
    public synchronized void save(E entity) {
        entityMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(E entity) {
        deleteById(entity.getId());
    }

    @Override
    public synchronized void deleteById(ID id) {
        entityMap.remove(id);
    }

    @Override
    public synchronized List<E> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(entityMap.values()));
    }

    @Override
    public void deleteAll() {
        entityMap.clear();
    }

    @Override
    public int size() {
        return entityMap.size();
    }

    @Override
    public E getById(ID id) {
        return entityMap.get(id);
    }
}
