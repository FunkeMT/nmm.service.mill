package model.impl;

import model.IJunction;
import model.IPlayer;
import model.IPlayerState;
import model.IPuck;
import util.NmmRuntimeException;

public class PlayerMOVE implements IPlayerState {

    IPlayer player;

    public PlayerMOVE(IPlayer player) {
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
    public void movePuck(IJunction jFrom, IJunction jTo, IPlayer currentPlayer) {

        if(jFrom == null || jTo == null) {
            throw new NmmRuntimeException("Illegal move, please check your coordinates.");
        }
        if(jTo.hasPuck()) {
            throw new NmmRuntimeException("There already is a Puck.");
        }
        if(jFrom.getPuck() == null) {
            throw new NmmRuntimeException("There is nothing to move.");
        }
        if(!jFrom.getPuck().getPlayer().equals(currentPlayer)) {
            throw new NmmRuntimeException("That's not your puck unfortunately.");
        }
        if (!checkMovement(jFrom, jTo)) {
            throw new NmmRuntimeException("Move is not allowed");
        }
    }

    private boolean checkMovement(IJunction jFrom, IJunction jTo) {
        if(jFrom.getDown() != null && jFrom.getDown().equals(jTo)) {
            return true;
        } else if(jFrom.getUp() != null && jFrom.getUp().equals(jTo)) {
            return true;
        } else if(jFrom.getRight() != null && jFrom.getRight().equals(jTo)) {
            return true;
        } else if(jFrom.getLeft() != null && jFrom.getLeft().equals(jTo)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "MOVE";
    }
}
