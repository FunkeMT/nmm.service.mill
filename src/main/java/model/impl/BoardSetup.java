package main.java.model.impl;

public abstract class BoardSetup {

    //Initial BoardImage Setup:
    //See BoardImage Template for reference
    //a1---------a4--------a7
    Junction a1 = new Junction();
    Junction a4 = new Junction();
    Junction a7 = new Junction();

    //----b2-----b4-----b6----
    Junction b2 = new Junction();
    Junction b4 = new Junction();
    Junction b6 = new Junction();

    //-------c3--c4--c5-------
    Junction c3 = new Junction();
    Junction c4 = new Junction();
    Junction c5 = new Junction();

    //d1--d2--d3-----d5--d6--d7
    Junction d1 = new Junction();
    Junction d2 = new Junction();
    Junction d3 = new Junction();
    Junction d5 = new Junction();
    Junction d6 = new Junction();
    Junction d7 = new Junction();

    //-------e3--e4--e5-------
    Junction e3 = new Junction();
    Junction e4 = new Junction();
    Junction e5 = new Junction();

    //----f2-----f4-----f6----
    Junction f2 = new Junction();
    Junction f4 = new Junction();
    Junction f6 = new Junction();

    //g1---------g4--------g7
    Junction g1 = new Junction();
    Junction g4 = new Junction();
    Junction g7 = new Junction();

}
