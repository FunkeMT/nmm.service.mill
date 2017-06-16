package model.impl;

import model.IJunction;
import model.IPlayer;
import model.IPuck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.HashMap;

/**
 * a IJunction knows its neighbours
 */
public class Junction extends AbstractButton implements IJunction {

    private IJunction up;
    private IJunction right;

    private IJunction down;
    private IJunction left;

    private IPuck puck;

    public Junction() {
        // nothing to do
    }

    public Junction(IPuck puck) {
        this.puck = puck;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    private boolean pressed;

    int x;
    int y;

    @Override
    public void setNeighbours(IJunction up, IJunction right, IJunction down, IJunction left) {
        this.up = up;
        this.right = right;
        this.down = down;
        this.left = left;
        this.puck = null;
    }

    public void placeOnGui(String name, int y, int x, MouseAdapter m) {

        this.setName(name);
        this.x = x;
        this.y = y;
        this.setBounds(x-15, y-15, 50, 50);

        this.addMouseListener(m);
    }

    @Override
    public void setUp(IJunction up) {
        this.up = up;
    }

    @Override
    public void setRight(IJunction right) {
        this.right = right;
    }

    @Override
    public void setDown(IJunction down) {
        this.down = down;
    }

    @Override
    public void setLeft(IJunction left) {
        this.left = left;
    }

    @Override
    public void setPuck(IPuck puck) {
        this.puck = puck;
    }

    @Override
    public IJunction getUp() {
        return up;
    }

    @Override
    public IJunction getRight() {
        return right;
    }

    @Override
    public IJunction getDown() {
        return down;
    }

    @Override
    public IJunction getLeft() {
        return left;
    }

    @Override
    public IPuck getPuck() {
        return puck;
    }

    @Override
    public boolean hasPuck() {
        if(this.puck == null) {
            return false;
        }
        return true;
    }

    //---------------------------
    // IMPLEMENTATIONS

    @Override
    public String toString() {
        if (this.puck == null) {
            return "x-";
        } else if (this.puck.getPlayer().getMan() == Player.Man.WHITE) {
            return "\u25CB-";
        } else {
            return "\u25CF-";
        }
    }

    public HashMap<String, Object> getData() {
        HashMap<String, Object> map = new HashMap<>();

        String man = null;
        if (this.puck != null) {
            man = this.puck.getPlayer().getMan().toString();
        }

        map.put("man", man);
        return map;
    }

    @Override
    protected void paintComponent(Graphics g) {

        this.repaint();
        if (this.getPuck() != null) {

            super.paintComponent(g);
            int radius = 25;
            int diameter = radius * 2;
            if (this.isPressed()) {
                g.setColor(Color.BLUE);
                g.fillOval(0, 0, diameter, diameter);
            } else if(this.getPuck().getPlayer().getMan() == IPlayer.Man.WHITE) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.BLACK);
            }

            //shift x and y by the radius of the circle in order to correctly center it
            g.fillOval(0, 0, diameter, diameter);
        }
    }


}
