package main.java.aview.http;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.*;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.http.javadsl.unmarshalling.Unmarshaller;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import main.java.controller.IJsonController;
import main.java.controller.impl.JsonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.complete;


public class HttpServer extends AllDirectives {

    private final IJsonController jsonController;

    private static final Logger logger = LogManager.getLogger(HttpServer.class.getName());

    public HttpServer() {
        this.jsonController = new JsonController();
    }

    public void run() {
        // boot up server using the route as defined below
        ActorSystem system = ActorSystem.create("routes");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = this.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost("localhost", 8081), materializer);

        this.logger.info("Server online at http://localhost:8081/\nPress RETURN to stop...");
        try {
            System.in.read(); // let it run until user presses return
        } catch (IOException e) {
            this.logger.error("HttpServer failed!");
            e.printStackTrace();
        }

        binding
                .thenCompose(ServerBinding::unbind) // trigger unbinding from the port
                .thenAccept(unbound -> system.terminate()); // and shutdown when done
    }

    private Route createRoute() {
        return route(

                path("checkMill", () ->
                        post(() -> entity(Unmarshaller.entityToString(), content ->
                                this.jsonController.checkMill(content)))
                ),

                path("hello", () ->
                        get(() -> complete(StatusCodes.OK))
                )

        );
    }
}