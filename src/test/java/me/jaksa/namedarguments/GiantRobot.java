package me.jaksa.namedarguments;


import static me.jaksa.namedarguments.GiantRobot.PunchParams.Names.*;
import static me.jaksa.namedarguments.Args.*;

public class GiantRobot {
    // standard
    public static class PunchParams {
        enum Names {FORCE, SPEED, EXCLAMATION}
        public static Arg force(int f) { return arg(FORCE, f); }
        public static Arg speed(int s) { return arg(SPEED, s); }
        public static Arg exclamation(String e) { return arg(EXCLAMATION, e); }
    }
    public void punch(Arg... args) {
        int force = getArg(args, FORCE, 10);
        int speed = getArg(args, SPEED, 3);
        String exclamation = getArg(args, EXCLAMATION, "@#$%!");

        System.out.printf("Robot, punching with force %d and speed %d, says: %s\n", force, speed, exclamation);
    }

    // extra safe
    public static class KickParam<T> extends Arg<T> {
        private KickParam(Names k, T v) { super(k, v); }
        enum Names {HEIGHT, FORCE, IS_ROUND}
        public static KickParam<Integer> height(int h) { return new KickParam<Integer>(Names.HEIGHT, h); }
        public static KickParam<Integer> force(int f) { return new KickParam<Integer>(Names.FORCE, f); }
        public static KickParam<Boolean> isRoundKick(boolean r) { return new KickParam<Boolean>(Names.IS_ROUND, r); }
        public static KickParam<Boolean> isRoundKick() { return new KickParam<Boolean>(Names.IS_ROUND, true); }
        public static KickParam<Boolean> isRoundKick = new KickParam<Boolean>(Names.IS_ROUND, true);
    }
    public void kick(KickParam<Integer> heightArg, KickParam... args) {
        int height = heightArg.value;
        int force = getArg(args, KickParam.Names.FORCE, 5);
        boolean isRoundKick = getArg(args, KickParam.Names.IS_ROUND, false);

        String heightDesc = (height > 10) ? "high" : "low";
        if (isRoundKick) System.out.printf("Robot performs a %s roundkick with force %d", heightDesc, force);
        else System.out.printf("Robot does a %s kick with force %d", heightDesc, force);
    }
}
