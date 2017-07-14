package server;

import server.task_manager.Task;
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

        Message message = null;

        while(true){

            message = communication.read();

            System.out.println(message.toString());

            Task task = new Task(message.getMapContent(), message.getType(),server.getHibernateUserService(), communication);
            server.getTaskQueue().add(task);
            System.out.println(task.toString());
        }

    }
}
