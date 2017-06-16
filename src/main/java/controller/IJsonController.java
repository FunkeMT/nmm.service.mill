package main.java.controller;

import akka.http.javadsl.server.Route;


public interface IJsonController {

    Route checkMill(String json);

}
