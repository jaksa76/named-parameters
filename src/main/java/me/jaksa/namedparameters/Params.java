package me.jaksa.namedparameters;



public class Params {
    public static <K, V> Param<V> param(K key, V value) {
        return new Param(key, value);
    }

    public static <K, V> V getParam(Param[] params, K key, V def) {
        for (Param a : params)
            if (a.key.equals(key)) return (V) a.value;
        return def;
    }
}
