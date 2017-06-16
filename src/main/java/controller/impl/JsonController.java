package main.java.controller.impl;

import akka.http.javadsl.model.ContentTypes;
import akka.http.javadsl.model.HttpEntities;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.Route;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import main.java.controller.IJsonController;
import main.java.model.IJunction;
import main.java.model.IPlayer;
import main.java.model.impl.Board;
import main.java.model.impl.Player;
import main.java.model.impl.Puck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Map;

import static akka.http.javadsl.server.Directives.complete;


public class JsonController implements IJsonController {

    private static final Logger logger = LogManager.getLogger(JsonController.class.getName());

    public JsonController() {

    }

    @Override
    public Route checkMill(String jsonStr) throws IllegalArgumentException {
        this.logger.info("Incoming checkMill call ...");

        // convert string to JsonNode
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = null;
        try {
            json = mapper.readTree(jsonStr);
        } catch (IOException e) {
            throw new IllegalArgumentException("Illegal Json format");
        }

        // game relevant objects
        IPlayer white = new Player("white", IPlayer.Man.WHITE);
        IPlayer black = new Player("black", IPlayer.Man.BLACK);
        Map<String, IJunction> boardMap = new Board().getBoardMap();

        // iterate over board and set junctions from Json
        JsonNode jsonBoard = json.findPath("board");
        for (Map.Entry<String, IJunction> entry : boardMap.entrySet()) {
            JsonNode nodeJunction = json.findPath(entry.getKey());

            if (nodeJunction.has("man")) {
                this.logger.debug("nodeJunction has man ...");
                String strMan = nodeJunction.path("man").textValue();

                if (strMan.equals(IPlayer.Man.BLACK.name())) {
                    entry.getValue().setPuck(new Puck(black));
                } else if (strMan.equals(IPlayer.Man.WHITE.name())) {
                    entry.getValue().setPuck(new Puck(white));
                } else {
                    continue;
                }

                boardMap.put(entry.getKey(), entry.getValue());
            }
        }


        // get move
        JsonNode jsonMove = json.findPath("move");
        String strMan = jsonMove.findPath("man").textValue();
        String strJunction = jsonMove.findPath("junction").textValue();
        IPlayer currPlayer = strMan.equals(IPlayer.Man.BLACK.name()) ? black : white;

        boolean mill = MillController.checkformill(boardMap.get(strJunction), currPlayer);
        this.logger.info("mill = " + (mill ? "true" : "false"));


        // build json response
        JsonObject response = new JsonObject();
        response.addProperty("mill", mill);


        return complete(
                StatusCodes.OK,
                HttpEntities.create(ContentTypes.APPLICATION_JSON, response.toString())
        );
    }
}
