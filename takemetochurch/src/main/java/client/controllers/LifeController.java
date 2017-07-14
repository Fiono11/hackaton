package client.controllers;

import client.Navigation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import shared.Communication;
import shared.Message;
import shared.MessageType;
import shared.Values;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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

    @FXML
    private Button btn_submit;

    private Communication communication;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        communication = Navigation.getInstance().getCommunication();
        //unnullifie();
    }

    @FXML
    public void addToDo() {
        TextField textField = new TextField();
    }

    @FXML
    public void back() {
        Navigation.getInstance().back();
    }

    @FXML
    public void submit() {

        Map<String, String> map = new HashMap<>();
        map.put(Values.TODO_1, toDo_1.getText());
        map.put(Values.TODO_2, toDo_2.getText());
        map.put(Values.TODO_3, toDo_3.getText());
        Message message = new Message(MessageType.LIFE, (HashMap<String, String>) map);
        communication.write(message);
    }

    public void unnullifie() {
        toDo_1.setText("");
        toDo_2.setText("");
        toDo_3.setText("");
    }
}
