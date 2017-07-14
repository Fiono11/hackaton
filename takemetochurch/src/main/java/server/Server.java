package server;

import server.service.HibernateUserService;
import shared.MessageType;

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
    private HibernateUserService hibernateUserService;

    public Server(int port) {
        this.port = port;
        taskQueue = new ConcurrentLinkedQueue<>();
    }

    public void init() throws IOException {
        hibernateUserService = new HibernateUserService();
        serverSocket = new ServerSocket(port);

    }

    public ConcurrentLinkedQueue<Task> getTaskQueue() {
        return taskQueue;
    }

    public HibernateUserService getHibernateUserService() {
        return hibernateUserService;
    }

    public void start() throws IOException {

        ExecutorService cachedPool = Executors.newCachedThreadPool();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                ExecutorService fixedPool = Executors.newFixedThreadPool(10);

                while (true){

                    if (!taskQueue.isEmpty()){
                        //TODO: to stop this thread, just create a shutdown task.
                        fixedPool.submit(taskQueue.poll());
                    }

                }

            }
        };

        runnable.run();

        while (true) {

            Socket clientSocket = serverSocket.accept();
            cachedPool.submit(new ClientHandler(clientSocket, this));

        }

        //taskQueue.add(new Task(null, MessageType.SHUTDOWN, null));

    }

    public void close() throws IOException {
        serverSocket.close();
    }
}
