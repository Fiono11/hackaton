package client;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by codecadet on 13/07/17.
 */
public class Navigation {

    private static Navigation instance = null;
    private Stage stage;
    private LinkedList<Scene> scenes;
    private Map<String, Initializable> controllers;

    private Navigation() {
        scenes = new LinkedList<>();
        controllers = new HashMap<>();
    }

    public static synchronized Navigation getInstance() {
        if (instance == null) {
            instance = new Navigation();
        }
        return instance;
    }

    public void loadScreen(String view) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/" + view + ".fxml"));
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 700, 400);
            scenes.push(scene);
            setScene(scene);
            controllers.put(view, fxmlLoader.getController());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void back() {
        if (scenes.size() > 1) {
            scenes.pop();
            setScene(scenes.peek());
        }
    }

    public void setScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }
}

