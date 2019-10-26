package me.jaksa.namedparameters;

/**
 * Created by Jaksa on 26/10/2019.
 */
public class Param<V> {
    public final Object key;
    public final V value;

    public Param(Object key, V value) {
        this.key = key;
        this.value = value;
    }
}
