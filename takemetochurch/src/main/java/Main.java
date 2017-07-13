

import client.Navigation;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Cyrille on 13/07/17.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("login");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
