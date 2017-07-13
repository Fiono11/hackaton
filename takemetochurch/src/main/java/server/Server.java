package server;

import shared.Communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by codecadet on 13/07/17.
 */
public class Server {

    private int port;
    private ServerSocket serverSocket;
    private ConcurrentLinkedQueue<Task> taskQueue;

    public Server(int port) {
        this.port = port;
        taskQueue = new ConcurrentLinkedQueue<>();
    }

    public void init() throws IOException {

        serverSocket = new ServerSocket(port);

    }

    public void start() throws IOException {

        ExecutorService cachedPool = Executors.newCachedThreadPool();

        while (true) {

            Socket clientSocket = serverSocket.accept();
            cachedPool.submit(new ClientHandler(clientSocket, taskQueue));

        }

    }

    public void close() throws IOException {
        serverSocket.close();
    }
}
