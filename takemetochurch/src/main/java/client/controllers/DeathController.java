package client.controllers;

import client.Navigation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import shared.Communication;
import shared.Message;
import shared.MessageType;
import shared.Values;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 13/07/17.
 */
public class DeathController implements Initializable {

    @FXML
    private Label submit_label;

    @FXML
    private Button btn_back;

    @FXML
    private ChoiceBox<String> bodyDestiny;

    @FXML
    private TextField religious;

    @FXML
    private TextField food;

    @FXML
    private TextField music;

    @FXML
    private TextField guests;

    @FXML
    private TextField special_request;

    @FXML
    private TextField budget;

    @FXML
    private Button btn_submit;

    @FXML
    private Button btn_delete;

    private boolean saved = false;
    private Communication communication;
    private ObservableList<String> bodyDestinies = FXCollections.observableArrayList("Cremated", "Buried", "Donated");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bodyDestiny.setItems(bodyDestinies);
        communication = Navigation.getInstance().getCommunication();
        //unnullifie();
        // addSound();
        communication.write(new Message(MessageType.DEATH_R, null));
        Message message = communication.read();

        if (!message.getMapContent().isEmpty()) {
            saved = true;
            populateFields(message);
        }
    }

    private void populateFields(Message message) {
        String string;
        religious.setText((string = message.getMapContent().get(Values.RELIGION)) != null ? string : "");
        food.setText((string = message.getMapContent().get(Values.FOOD)) != null ? string : "");
        music.setText((string = message.getMapContent().get(Values.MUSIC)) != null ? string : "");
        guests.setText((string = message.getMapContent().get(Values.NUMBER_OF_GUESTS)) != null ? string : "");
        special_request.setText((string = message.getMapContent().get(Values.SPECIAL_REQUEST)) != null ? string : "");
        budget.setText((string = message.getMapContent().get(Values.BUDGET)) != null ? string : "");
        if (message.getMapContent().get(Values.BODYTREAMENT) != null) {
            bodyDestiny.getSelectionModel().select(message.getMapContent().get(Values.BODYTREAMENT));
        }

    }

    @FXML
    public void back() {
        Navigation.getInstance().back();
    }

    public void submit() {
        Map<String, String> map = new HashMap<>();
        map.put(Values.BODYTREAMENT, bodyDestiny.getSelectionModel().getSelectedItem());
        map.put(Values.RELIGION, religious.getText());
        map.put(Values.FOOD, food.getText());
        map.put(Values.MUSIC, music.getText());
        map.put(Values.NUMBER_OF_GUESTS, guests.getText());
        map.put(Values.SPECIAL_REQUEST, special_request.getText());
        map.put(Values.BUDGET, budget.getText());
        Message message = new Message((saved ? MessageType.DEATH_U : MessageType.DEATH_C), (HashMap<String, String>) map);
        communication.write(message);
    }

    public void unnullifie() {
        religious.setText("");
        food.setText("");
        music.setText("");
        guests.setText("");
        special_request.setText("");
        budget.setText("");
    }

    public void delete(ActionEvent actionEvent) {



        unnullifie();
        communication.write(new Message(MessageType.DEATH_D, null));
        saved = false;

    }

    /*public void addSound() {
        new MediaPlayer(new Media(getClass().getResource("/death.mp3").toString())).play();
    }*/
}
