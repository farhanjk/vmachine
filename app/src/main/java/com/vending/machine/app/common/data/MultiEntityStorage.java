package com.vending.machine.app.common.data;

public interface MultiEntityStorage<E extends Entity, ID> extends MultiObjectStorage<E> {
    E getById(ID id);

    void deleteById(ID id);
}
