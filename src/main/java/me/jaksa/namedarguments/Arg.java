package me.jaksa.namedarguments;

/**
 * Created by Jaksa on 26/10/2019.
 */
public class Arg<V> {
    public final Object key;
    public final V value;

    public Arg(Object key, V value) {
        this.key = key;
        this.value = value;
    }
}
