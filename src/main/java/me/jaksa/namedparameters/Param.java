package me.jaksa.namedparameters;

/**
 * An object wrapping the named parameter. This class can be used as it is
 * or it can be extended to make the uses type safe.
 * <br>
 * The standard way to use it:
 * <pre>
 * public static class PunchParams {
 *   enum Names {FORCE, SPEED, EXCLAMATION}
 *   public static Param force(int f) { return param(FORCE, f); }
 *   public static Param speed(int s) { return param(SPEED, s); }
 *   public static Param exclamation(String e) { return param(EXCLAMATION, e); }
 * }
 *
 * public void punch(Param... params) {
 *   int force = getParam(params, FORCE, 10);
 *   int speed = getParam(params, SPEED, 3);
 *   String exclamation = getParam(params, EXCLAMATION, "@#$%!");
 *
 *   ...
 * }
 * </pre>
 *
 * The extra safe way to use it:
 *
 * <pre>
 *  public static class KickParam<T> extends Param<T> {
 *    private KickParam(Names k, T v) { super(k, v); }
 *    enum Names {HEIGHT, FORCE, IS_ROUND}
 *    public static KickParam<Integer> height(int h) { return new KickParam<Integer>(Names.HEIGHT, h); }
 *    public static KickParam<Integer> force(int f) { return new KickParam<Integer>(Names.FORCE, f); }
 *    public static KickParam<Boolean> roundKick = new KickParam<Boolean>(Names.IS_ROUND, true);
 *  }
 *
 *  public void kick(KickParam<Integer> heightParam, KickParam... extraParams) {
 *      int height = heightParam.value;
 *      int force = getParam(extraParams, KickParam.Names.FORCE, 5);
 *      boolean isRoundKick = getParam(extraParams, KickParam.Names.IS_ROUND, false);
 *
 *      ...
 *  }
 * </pre>
 *
 * @see Params
 */
public class Param<V> {
    /** The name of the parameter. Could be a string or an enum. */
    public final Object name;

    /** the value of the parameter */
    public final V value;

    /**
     * @param name the name of the parameter (can be an enum)
     * @param value the actual value
     */
    public Param(Object name, V value) {
        this.name = name;
        this.value = value;
    }
}
