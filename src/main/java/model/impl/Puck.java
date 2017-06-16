package main.java.model.impl;

import main.java.model.IPlayer;
import main.java.model.IPuck;

public class Puck implements IPuck {

    private IPlayer player;

    public Puck() {
        this.player = null;
    }

    public Puck(IPlayer player) {
        this.player = player;
    }

    @Override
    public void setPlayer(IPlayer player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return (Player) this.player;
    }

}