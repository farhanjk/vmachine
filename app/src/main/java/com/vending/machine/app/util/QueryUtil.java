package com.vending.machine.app.util;

import java.util.ArrayList;
import java.util.List;

public class QueryUtil {

    public interface Select<T, R> {
        R select(T t);
    }

    public static <T, R> List<R> select(Iterable<T> list, Select<T, R> expression) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(expression.select(t));
        }
        return result;
    }

}
