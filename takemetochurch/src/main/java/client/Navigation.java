package client;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shared.Communication;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Cyrille on 13/07/17.
 */
public class Navigation {

    private final String PREFIX = "/";
    private final String SUFFIX = ".fxml";

    private static Navigation instance;
    private Communication communication;

    private LinkedList<Scene> scenes = new LinkedList<>();
    private Map<String, Initializable> controllers = new HashMap<>();

    private Stage stage;
    private Scene scene;

    private Navigation() {}


    public void loadScreen(String view) {
        try {

            String path = PREFIX + view + SUFFIX;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));
            Parent root = fxmlLoader.load();
            root.getStylesheets().add("/css/style.css");

            controllers.put(view, fxmlLoader.<Initializable>getController());

            scene = new Scene(root);
            scenes.push(scene);

            setScene(scene);

        } catch (IOException e) {
            System.err.println("Failure to load view " + view + " : " + e.getMessage());
        }
    }


    public void back() {

        if (scenes.size() < 2) {

            return;
        }

        scenes.pop();
        setScene(scenes.peek());
    }

    public static Navigation getInstance() {

        if (instance == null) {

            synchronized (Navigation.class) {

                if (instance == null) {
                    instance = new Navigation();
                }
            }
        }

        return instance;
    }


    public <T extends Initializable> T getController(String view) {

        return (T)controllers.get(view);
    }


    public void setScene(Scene scene) {
        stage.setScene(scene);
        scene.getStylesheets().add("/stylesheet/styles.css");
        stage.setResizable(false);
        stage.show();
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Communication getCommunication() {
        return communication;
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
    }
}

