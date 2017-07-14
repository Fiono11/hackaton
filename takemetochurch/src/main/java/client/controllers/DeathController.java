package client.controllers;

import client.Navigation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    @FXML
    private TextField music;

    private boolean saved = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bodyDestiny.setItems(bodyDestinies);
        communication = Navigation.getInstance().getCommunication();
        //unnullifie();
        addSound();
        communication.write(new Message(MessageType.DEATH_R,null));
        Message message = communication.read();

        if(message.getMapContent() != null){
            saved = true;
            populateFields(message);
        }
    }

    private void populateFields(Message message) {
        String string;
        location.setText((string = message.getMapContent().get(Values.LOCATION)) != null ? string: "");
        officiator.setText((string = message.getMapContent().get(Values.OFFICIATOR)) != null ? string: "");
        contact.setText((string = message.getMapContent().get(Values.CONTACT)) != null ? string: "");
        music.setText((string = message.getMapContent().get(Values.MUSIC)) != null ? string: "");
        if(message.getMapContent().get(Values.LOCATION) != null){
            bodyDestiny.getSelectionModel().select(message.getMapContent().get(Values.LOCATION));
        }

    }

    @FXML
    public void back() {
        Navigation.getInstance().back();
    }

    public void submit() {
        Map<String, String> map = new HashMap<>();
        map.put(Values.BODYTREAMENT, bodyDestiny.getSelectionModel().getSelectedItem());
        map.put(Values.LOCATION, location.getText());
        map.put(Values.OFFICIATOR, officiator.getText());
        map.put(Values.CONTACT, contact.getText());
        map.put(Values.MUSIC, music.getText());
        Message message = new Message((saved ? MessageType.DEATH_U : MessageType.DEATH_C ) , (HashMap<String, String>) map);
        communication.write(message);
    }

    public void unnullifie() {
        location.setText("");
        officiator.setText("");
        contact.setText("");
        music.setText("");
    }

    public void delete(ActionEvent actionEvent) {

        unnullifie();
        communication.write(new Message(MessageType.DEATH_D, null));
        saved = false;

    }

    public void addSound() {
        new MediaPlayer(new Media(getClass().getResource("/sounds/death.mp3").toString())).play();
    }
}
