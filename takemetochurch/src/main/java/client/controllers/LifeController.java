package client.controllers;

import client.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 13/07/17.
 */
public class LifeController implements Initializable {

    @FXML
    private TextField toDo_1;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_forward;

    @FXML
    private TextField toDo_2;

    @FXML
    private TextField toDo_3;

    @FXML
    private ScrollPane scrollPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void addToDo() {
        TextField textField = new TextField();
    }

    @FXML
    public void back() {
        Navigation.getInstance().back();
    }
}
