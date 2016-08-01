package net.jeeshop.core.util.transform;


import net.jeeshop.core.util.Pair;

public abstract class MapEntryTransform<T, K, V> extends AbstractTransform<T, Pair<K, V>> {
    public Pair<K, V> convert(T t) {
        Pair<K, V> entry = new Pair<K, V>();
        entry.setKey(convertKey(t));
        entry.setValue(convertValue(t));
        return entry;
    }

    public abstract K convertKey(T t);

    public abstract V convertValue(T t);
}