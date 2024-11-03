package com.bastien_corre.cleanjava.core.utils;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;


public class ListUtils extends AbstractList<Object> {
    protected final List<Object> internalList = new ArrayList<>();

    @Override
    public Object get(int index) {
        return internalList.get(index);
    }

    @Override
    public int size() {
        return internalList.size();
    }

    public static <T> List<T> requireNonNullNorEmptyElse(List<T> list, List<T> defaultList) {
        if (list == null || list.isEmpty()) {
            return defaultList;
        }
        return list;
    }
}
