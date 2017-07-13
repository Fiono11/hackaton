package client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 13/07/17.
 */
public class DeathController implements Initializable {

    private ObservableList<String> bodyDestinies = FXCollections.observableArrayList("Cremated", "Buried");

    @FXML
    private ChoiceBox<String> bodyDestiny;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bodyDestiny.setItems(bodyDestinies);
    }
}
