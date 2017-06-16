package main.java.model.impl;

import main.java.model.IJunction;
import main.java.model.IPlayer;
import main.java.model.IPlayerState;
import main.java.model.IPuck;
import main.java.util.NmmRuntimeException;


public class PlayerPICK implements IPlayerState {

    Player player;

    public PlayerPICK(Player player) {
        this.player = player;
    }

    @Override
    public void setPuck(IJunction j, IPuck puck, IPlayer cur) {
        throw new NmmRuntimeException("No more Pucks to set.");
    }

    @Override
    public void pickPuck(IJunction j, IPlayer cur) {

        if(j == null) {
            throw new NmmRuntimeException("Illegal move, please check your coordinates.");
        }
        if (!j.hasPuck()) {
            throw new NmmRuntimeException("There is no puck to take away.");
        }
        if (j.getPuck().getPlayer().equals(cur)) {
            throw new NmmRuntimeException("This is your own puck!");
        }
    }

    @Override
    public void movePuck(IJunction from, IJunction to, IPlayer cur) {
        throw new NmmRuntimeException("Don't you want to pick an enemy puck? (They won't enjoy that.)");
    }

    @Override
    public String toString() {
        return "PICK";
    }
}
