package main.java.model;

public interface IPlayerState {

    void setPuck(IJunction j, IPuck puck, IPlayer currentPlayer);

    void pickPuck(IJunction j, IPlayer currentPlayer);

    void movePuck(IJunction jFrom, IJunction jTo, IPlayer currentPlayer);

    String toString();

}
