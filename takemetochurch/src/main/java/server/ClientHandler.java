package server;

import shared.Communication;
import shared.Message;

import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by codecadet on 13/07/17.
 */
public class ClientHandler implements Runnable {

    private Communication communication;
    private ConcurrentLinkedQueue<Task> taskQueue;

    public ClientHandler(Socket clientSocket, ConcurrentLinkedQueue<Task> taskQueue) {
        this.communication = new Communication(clientSocket);
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {

        communication.openStreams();
        authentification();

        readClient();

        communication.close();

    }

    private void readClient() {

    }

    private void authentification() {

        boolean logged = false;
        Message message = null;

        while(!logged){

            message = communication.read();

            switch (message.getType()){

                case LOGIN:
                    Task task = new Task(message.getType(),message.getMapContent());
                    break;
                case REGISTRY:
                    break;
            }

        }

    }
}
