package main.java.model.impl;

import main.java.model.IJunction;
import main.java.model.IPlayer;
import main.java.model.IPlayerState;
import main.java.model.IPuck;
import main.java.util.NmmRuntimeException;

public class PlayerHOP implements IPlayerState {

    Player player;

    public PlayerHOP(Player player) {
        this.player = player;
    }

    @Override
    public void setPuck(IJunction j, IPuck puck, IPlayer cur) {
        throw new NmmRuntimeException("No more Pucks to set.");
    }

    @Override
    public void pickPuck(IJunction j, IPlayer cur) {
        throw new NmmRuntimeException("You don't have a valid mill, you can't pick yet.");
    }

    @Override
    public void movePuck(IJunction jFrom, IJunction jTo, IPlayer cur) {

        if(jFrom == null || jTo == null) {
            throw new NmmRuntimeException("Illegal move, please check your coordinates.");
        }
        if(jTo.hasPuck()) {
            throw new NmmRuntimeException("There already is a Puck.");
        }
        if(!jFrom.getPuck().getPlayer().equals(cur)) {
            throw new NmmRuntimeException("That's not your puck unfortunately.");
        }
    }

    @Override
    public String toString() {
        return "HOP";
    }
}
