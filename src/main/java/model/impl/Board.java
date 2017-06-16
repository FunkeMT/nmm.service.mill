package main.java.model.impl;

import main.java.model.IBoard;
import main.java.model.IJunction;

import java.util.HashMap;
import java.util.Map;

/**
 * Builds and Returns the main board
 * The BoardImage is comprised of Junctions
 * 24 junctions
 */
public class Board extends BoardSetup implements IBoard {

    private Map<String, IJunction> boardMap;

    public Board() {
        boardMap = new HashMap<>();

        //Connections
        //directions clockwise: up, right, down, left
        //a1---------a4--------a7
        a1.setNeighbours(null, d1, a4, null);
        a4.setNeighbours(a1, b4, a7, null);
        a7.setNeighbours(a4, d7, null, null);

        //----b2-----b4-----b6----
        b2.setNeighbours(null, d2, b4, null);
        b4.setNeighbours(b2, c4, b6, a4);
        b6.setNeighbours(b4, d6, null, null);

        //-------c3--c4--c5-------
        c3.setNeighbours(null, d3, c4, null);
        c4.setNeighbours(c3, null, c5, b4);
        c5.setNeighbours(c4, d5, null, null);

        //d1--d2--d3-----d5--d6--d7
        d1.setNeighbours(null, g1, d2, a1);
        d2.setNeighbours(d1, f2, d3, b2);
        d3.setNeighbours(d2, e3, null, c3);
        d5.setNeighbours(null, e5, d6, c5);
        d6.setNeighbours(d5, f6, d7, b6);
        d7.setNeighbours(d6, g7, null, a7);

        //-------e3--e4--e5-------
        e3.setNeighbours(null, null, e4, d3);
        e4.setNeighbours(e3, f4, e5, null);
        e5.setNeighbours(e4, null, null, d5);

        //----f2-----f4-----f6----
        f2.setNeighbours(null, null, f4, d2);
        f4.setNeighbours(f2, g4, f6, e4);
        f6.setNeighbours(f4, null, null, d6);

        //g1---------g4--------g7
        g1.setNeighbours(null, null, g4, d1);
        g4.setNeighbours(g1, null, g7, f4);
        g7.setNeighbours(g4, null, null, d7);

        this.boardMap.put("a1", a1);
        this.boardMap.put("a4", a4);
        this.boardMap.put("a7", a7);

        this.boardMap.put("b2", b2);
        this.boardMap.put("b4", b4);
        this.boardMap.put("b6", b6);

        this.boardMap.put("c3", c3);
        this.boardMap.put("c4", c4);
        this.boardMap.put("c5", c5);

        this.boardMap.put("d1", d1);
        this.boardMap.put("d2", d2);
        this.boardMap.put("d3", d3);
        this.boardMap.put("d5", d5);
        this.boardMap.put("d6", d6);
        this.boardMap.put("d7", d7);

        this.boardMap.put("e3", e3);
        this.boardMap.put("e4", e4);
        this.boardMap.put("e5", e5);

        this.boardMap.put("f2", f2);
        this.boardMap.put("f4", f4);
        this.boardMap.put("f6", f6);

        this.boardMap.put("g1", g1);
        this.boardMap.put("g4", g4);
        this.boardMap.put("g7", g7);

    }

    @Override
    public Map<String, IJunction> getBoardMap() {
        return boardMap;
    }

    @Override
    public HashMap<String, Object> getData() {
        HashMap<String, Object> data = new HashMap<>();
        this.boardMap.forEach((key, value) -> {
            data.put(key, value.getData());
        });
        return data;
    }
}