import aview.http.HttpServer;

import java.util.Scanner;


public final class Service {

    private static Scanner scanner;
    private static HttpServer httpServer;
    private static Service instance;


    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public Service() {
        httpServer = new HttpServer();
        httpServer.run();
    }

    public static void main(String[] args) {
        Service.getInstance();
    }
}