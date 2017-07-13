

import client.Navigation;
import javafx.application.Application;
import javafx.stage.Stage;
import server.model.User;
import server.model.dao.UserDao;
import server.persistence.hibernate.HibernateTransactionManager;
import server.service.HibernateUserService;

/**
 * Created by Cyrille on 13/07/17.
 */
public class Main extends Application {

    public void init(){
        HibernateUserService hibernateUserService = new HibernateUserService(new UserDao(), new HibernateTransactionManager());

        hibernateUserService.addUser(new User());
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("login");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
