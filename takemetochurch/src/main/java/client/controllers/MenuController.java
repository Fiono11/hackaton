package client.controllers;

import client.Navigation;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 13/07/17.
 */
public class MenuController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void chooseDeath() {
        Navigation.getInstance().loadScreen("death");
    }

    public void chooseLife() {
        Navigation.getInstance().loadScreen("life");
    }
}
