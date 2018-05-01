package com.vending.machine.app.common.data;

import android.support.annotation.Nullable;

public interface SingleObjectStorage<T> {
    @Nullable
    T get();

    void save(T object);

    void delete();
}
