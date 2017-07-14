package server;

import server.model.dao.UserDao;
import server.persistence.hibernate.HibernateTransactionManager;
import server.service.HibernateUserService;
import server.service.UserService;
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

        while (true) {

            Socket clientSocket = serverSocket.accept();
            cachedPool.submit(new ClientHandler(clientSocket, this));

        }

    }

    public void close() throws IOException {
        serverSocket.close();
    }
}
