package server;

import shared.Values;

import java.io.IOException;

/**
 * Created by codecadet on 13/07/17.
 */
public class MainServer {

    public static void main(String[] args) {

        Server server = new Server(Values.PORT);

        try {
            server.init();
            server.start();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
