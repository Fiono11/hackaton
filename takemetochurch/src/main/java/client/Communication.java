package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by codecadet on 13/07/17.
 */
public class Communication {

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Socket socket;

    public Communication(Socket socket) {
        this.socket = socket;
    }

    public void openStreams() {
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Comunication fudeu");
        }
    }


    public Message read() {

        Message message = null;

        try {
            message = (Message) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fudeu no read");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("fudeu no read");
        }

        return message;

    }

    public void close() {

        //TODO check if this gives problems
        try {

            objectInputStream.close();
            objectOutputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("communication close fudeu");
        }
    }

}
