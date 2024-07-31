package io.github.asdqwenm123;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class KeyKey<K1, K2> {
    private final HashMap<K1, K2> map1;
    private final HashMap<K2, K1> map2;

    public KeyKey() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
    }

    public void put(K1 k1, K2 k2) {
        map1.put(k1, k2);
        map2.put(k2, k1);
    }

    public Object get(Object k) {
        if (map1.containsKey(k)) {
            return map1.get(k);
        } else if (map2.containsKey(k)) {
            return map2.get(k);
        } else {
            throw new NoSuchElementException();
        }
    }

    public void remove(Object k) {
        if (contains(k)) {
            if (map1.containsKey(k)) {
                map2.remove(get(k), k);
                map1.remove(k);
            } else if (map2.containsKey(k)) {
                map1.remove(get(k), k);
                map2.remove(k);
            }
        }
    }

    public boolean contains(Object k) {
        if (map1.containsKey(k)) {
            return true;
        } else return map2.containsKey(k);
    }
}
