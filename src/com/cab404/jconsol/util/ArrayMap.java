package com.cab404.jconsol.util;

import java.util.*;

/**
 * Simple array of map entries
 *
 * @author cab404
 */
public class ArrayMap<K, V> {
    List<Map.Entry<K, V>> data = new ArrayList<>();

    public void put(K key, V value) {
        AbstractMap.SimpleEntry<K, V> entry = new AbstractMap.SimpleEntry<>(key, value);
        data.add(entry);
    }

    public Iterable<V> getValues(final K key) {
        return new Iterable<V>() {
            @Override public Iterator<V> iterator() {
                return new Iterator<V>() {
                    int index;

                    private void skip() {
                        while (index < data.size() && !data.get(index).getKey().equals(key)) {index++;}
                    }

                    @Override public boolean hasNext() {
                        for (int i = index; i < data.size(); i++)
                            if (data.get(index).getKey().equals(key)) return true;
                        return false;
                    }

                    @Override public V next() {
                        skip();
                        if (data.get(index).getKey().equals(key))
                            return data.get(index++).getValue();
                        throw new NoSuchElementException();
                    }

                    @Override public void remove() {
                        data.remove(index);
                    }
                };
            }
        };
    }

    @Override public String toString() {
        return data.toString();
    }
}
