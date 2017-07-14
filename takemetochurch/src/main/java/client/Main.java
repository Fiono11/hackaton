package client;

import javafx.application.Application;
import javafx.stage.Stage;
import server.model.Life;
import server.model.dao.UserDao;
import shared.Communication;
import shared.Values;
import server.model.Death;
import server.model.Life;
import server.model.User;
import server.service.HibernateUserService;
import shared.Values;

import java.net.Socket;

/**
 * Created by Cyrille on 13/07/17.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Communication communication = new Communication(new Socket(Values.IP, Values.PORT));
        communication.openStreams();
        Navigation.getInstance().setCommunication(communication);
        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("login");
        System.out.println(communication);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
