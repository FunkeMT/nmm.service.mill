package controller.impl;

import model.IJunction;
import model.IPlayer;

import java.lang.reflect.Method;

/**
 * Delegate of GameController
 */
public class MillController {

    public static boolean checkformill(IJunction j, IPlayer p) {
        int mill = -1;
        mill += checkformillR(j, 0, "Down", p);
        mill += checkformillR(j, 0, "Up", p);
        if (mill >= 3) {
            return true;
        }

        mill = -1;
        mill += checkformillR(j, 0, "Left", p);
        mill += checkformillR(j, 0, "Right", p);
        if (mill >= 3) {
            return true;
        }
        return false;
    }

    private static int checkformillR(IJunction j, int sum, String direction, IPlayer p) {
        int t = 1;
        Method method;

        String m = "get" + direction;
        try {
            method = j.getClass().getMethod(m);

            if (method.invoke(j) != null) {
                if (((IJunction) method.invoke(j)).hasPuck() &&
                        ((IJunction) method.invoke(j)).getPuck().getPlayer().equals(p)) {
                    t += sum;
                    t += checkformillR((IJunction) method.invoke(j), sum + 1, direction, p);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t;
    }
}