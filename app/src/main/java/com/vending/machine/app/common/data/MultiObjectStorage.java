package com.vending.machine.app.common.data;

import java.util.List;


public interface MultiObjectStorage<O> {
    void save(O object);

    List<O> getAll();

    void delete(O object);

    void deleteAll();

    int size();
}
