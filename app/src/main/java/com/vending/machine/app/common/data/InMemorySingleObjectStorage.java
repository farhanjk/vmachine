package com.vending.machine.app.common.data;

public class InMemorySingleObjectStorage<T> implements SingleObjectStorage<T> {
    private T data;

    public InMemorySingleObjectStorage() {
    }

    public T get() {
        return this.data;
    }

    public void save(T object) {
        this.data = object;
    }

    public void delete() {
        this.data = null;
    }
}
