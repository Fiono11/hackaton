package server;

import shared.Communication;
import shared.Message;

import java.net.Socket;

/**
 * Created by codecadet on 13/07/17.
 */
public class ClientHandler implements Runnable {

    private Communication communication;
    private Server server;

    public ClientHandler(Socket clientSocket, Server server) {
        this.communication = new Communication(clientSocket);
        this.server = server;
    }

    @Override
    public void run() {

        communication.openStreams();
        readClient();
        communication.close();

    }

    private void readClient() {

        boolean logged = false;
        Message message = null;

        while(!logged){

            message = communication.read();

            Task task = new Task(message.getMapContent(), message.getType(),server.getHibernateUserService(), communication);
            task.setUpStrategy(message.getType());
            server.getTaskQueue().add(task);

        }

    }
}
