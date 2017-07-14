package client.controllers;

import client.Navigation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

    private ObservableList<String> bodyDestinies = FXCollections.observableArrayList("Cremated", "Buried", "Donated");

    @FXML
    private Button btn_back;

    @FXML
    private ChoiceBox<String> bodyDestiny;

    private Communication communication;

    @FXML
    private Button btn_submit;

    @FXML
    private TextField location;

    @FXML
    private TextField officiator;

    @FXML
    private TextField contact;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bodyDestiny.setItems(bodyDestinies);
        communication = Navigation.getInstance().getCommunication();
        //unnullifie();
        addSound();
    }

    @FXML
    public void back() {
        Navigation.getInstance().back();
    }

    public void submit() {
        Map<String, String> map = new HashMap<>();
        map.put(Values.LOCATION, location.getText());
        map.put(Values.OFFICIATOR, officiator.getText());
        map.put(Values.CONTACT, contact.getText());
        Message message = new Message(MessageType.DEATH, (HashMap<String, String>) map);
        communication.write(message);
    }

    public void unnullifie() {
        location.setText("");
        officiator.setText("");
        contact.setText("");
    }

    public void addSound() {
        new MediaPlayer(new Media(getClass().getResource("/death.mp3").toString())).play();
    }
}
