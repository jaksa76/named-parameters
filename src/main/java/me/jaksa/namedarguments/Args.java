package me.jaksa.namedarguments;



public class Args {
    public static <K, V> Arg<V> arg(K key, V value) {
        return new Arg(key, value);
    }

    public static <K, V> V getArg(Arg[] args, K key, V def) {
        for (Arg a : args)
            if (a.key.equals(key)) return (V) a.value;
        return def;
    }
}
