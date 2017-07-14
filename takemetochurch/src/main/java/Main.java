

import client.Navigation;
import javafx.application.Application;
import javafx.stage.Stage;
import server.model.Death;
import server.model.Life;
import server.model.User;
import server.service.HibernateUserService;
import shared.Values;

/**
 * Created by Cyrille on 13/07/17.
 */
public class Main extends Application {

    public void init(){
        HibernateUserService hibernateUserService = new HibernateUserService();

        Death death = new Death("sim","sim","sim",10,"sim",1000);

        Life life = new Life("oi");
        User user = new User("cyrokasss", "cyrillessss", "feijo", "123456", "asddf@asdf.com");

        user.setDeath(death);
        user.setLife(life);


        hibernateUserService.addData(Values.USERDAO,user);

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
