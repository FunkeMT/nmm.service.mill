package main.java.controller.impl;

import main.java.model.IJunction;
import main.java.model.IPlayer;

import java.lang.reflect.Method;

/**
 * Delegate of GameController
 */
public class MillController {

//    private final GameController gameController;
//
//    public MillController(GameController gameController) {
//        this.gameController = gameController;
//    }
//
//    public void millAfterMove(IJunction j) {
//        if (checkformill(j, gameController.getCurrentIPlayer())) {
//            gameController.addStatusMessage("Congratulations, Sir!\n" +
//                    "You may now pick one of your opponents pucks that is not part of a mill.");
//            gameController.getCurrentIPlayer().setStatus(gameController.getCurrentIPlayer().getPICK());
//        } else {
//            gameController.changePlayer();
//        }
//    }

    // public to be testable TODO
    public boolean checkformill(IJunction j, IPlayer p) {
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

    private int checkformillR(IJunction j, int sum, String direction, IPlayer p) {
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