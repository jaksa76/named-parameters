package me.jaksa.namedparameters;


import me.jaksa.namedparameters.GiantRobot.KickParam;
import org.junit.Test;

import static me.jaksa.namedparameters.GiantRobot.KickParam.isRoundKick;
import static me.jaksa.namedparameters.GiantRobot.KickParam.height;
import static me.jaksa.namedparameters.GiantRobot.PunchParams.*;

/**
 * Created by Jaksa on 26/10/2019.
 */
public class ParamTest {
    GiantRobot robot = new GiantRobot();

    @Test
    public void testInvokingWithNamedParameters() {
        robot.punch();
        robot.punch(force(5));
        robot.punch(speed(30));
        robot.punch(force(1), speed(100));
        robot.punch(speed(100), force(1));
        robot.punch(force(12), exclamation("Take this!"));
    }

    @Test
    public void testInvokingExtraSafeMethod() throws Exception {
        // kick height is mandatory
        robot.kick(height(7));

        // will not compile becuase height is mandatory here
        // robot.kick();

        // will not compile because isRoundKick() is boolean
        // robot.kick(isRoundKick(true));

        // will not compile becuase speed() is a punch parameter
        // robot.kick(speed(10))


        // all these are equivalent
        robot.kick(height(12), isRoundKick(true));
        robot.kick(height(12), isRoundKick()); // param constructor with default value
        robot.kick(height(12), isRoundKick);   // this is really a constant

        // will not compile because here we've statically imported the force from punch
        // robot.kick(height(3), force(10));

        robot.kick(height(3), KickParam.force(100)); // we need to be explicit in this case
    }
}