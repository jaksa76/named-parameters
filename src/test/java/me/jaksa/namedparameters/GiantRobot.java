package me.jaksa.namedparameters;


import static me.jaksa.namedparameters.GiantRobot.PunchParams.Names.*;
import static me.jaksa.namedparameters.Params.*;

public class GiantRobot {
    // standard
    public static class PunchParams {
        enum Names {FORCE, SPEED, EXCLAMATION}
        public static Param force(int f) { return param(FORCE, f); }
        public static Param speed(int s) { return param(SPEED, s); }
        public static Param exclamation(String e) { return param(EXCLAMATION, e); }
    }
    public void punch(Param... params) {
        int force = getParam(params, FORCE, 10);
        int speed = getParam(params, SPEED, 3);
        String exclamation = getParam(params, EXCLAMATION, "@#$%!");

        System.out.printf("Robot, punching with force %d and speed %d, says: %s\n", force, speed, exclamation);
    }

    // extra safe
    public static class KickParam<T> extends Param<T> {
        private KickParam(Names k, T v) { super(k, v); }
        enum Names {HEIGHT, FORCE, IS_ROUND}
        public static KickParam<Integer> height(int h) { return new KickParam<Integer>(Names.HEIGHT, h); }
        public static KickParam<Integer> force(int f) { return new KickParam<Integer>(Names.FORCE, f); }
        public static KickParam<Boolean> isRoundKick(boolean r) { return new KickParam<Boolean>(Names.IS_ROUND, r); }
        public static KickParam<Boolean> isRoundKick() { return new KickParam<Boolean>(Names.IS_ROUND, true); }
        public static KickParam<Boolean> isRoundKick = new KickParam<Boolean>(Names.IS_ROUND, true);
    }
    public void kick(KickParam<Integer> heightParam, KickParam... extraParams) {
        int height = heightParam.value;
        int force = getParam(extraParams, KickParam.Names.FORCE, 5);
        boolean isRoundKick = getParam(extraParams, KickParam.Names.IS_ROUND, false);

        String heightDesc = (height > 10) ? "high" : "low";
        if (isRoundKick) System.out.printf("Robot performs a %s roundkick with force %d", heightDesc, force);
        else System.out.printf("Robot does a %s kick with force %d", heightDesc, force);
    }
}
