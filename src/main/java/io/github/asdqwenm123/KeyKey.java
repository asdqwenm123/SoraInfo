package io.github.asdqwenm123;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

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
                map2.remove(get(k));
                map1.remove(k);
            } else if (map2.containsKey(k)) {
                map1.remove(get(k));
                map2.remove(k);
            }
        }
    }

    public boolean contains(Object k) {
        if (map1.containsKey(k)) {
            return true;
        } else return map2.containsKey(k);
    }

    public HashMap<K1, K2> getMap1() {
        return map1;
    }

    public HashMap<K2, K1> getMap2() {
        return map2;
    }

//    public Set<K1> K1s() {
//        return map1.keySet();
//    }
//
//    public Set<K2> K2s() {
//        return map2.keySet();
//    }

    public void putAll(KeyKey<K1, K2> m) {
        map1.putAll(m.getMap1());
        map2.putAll(m.getMap2());
    }
}
