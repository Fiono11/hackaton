package client.controllers;

import client.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import client.utils.Verification;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import shared.Communication;
import shared.Message;
import shared.MessageType;
import shared.Values;
import javafx.scene.layout.GridPane;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField tf_username;

    @FXML
    private Label lbl_username;

    @FXML
    private PasswordField tf_password;

    @FXML
    private Label lbl_password;

    @FXML
    private Button btn_login;

    @FXML
    private Hyperlink link_register;

    @FXML
    private Label lbl_logininfo;

    @FXML
    private GridPane back_login;

    private Communication communication;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        communication = Navigation.getInstance().getCommunication();
    }

    @FXML
    void onLogin(ActionEvent event) {
        Verification.cleanLoginMsg(lbl_username, lbl_password, lbl_logininfo);


        if (!emptyField()) {

            lbl_logininfo.setVisible(false);
            Map<String, String> map = new HashMap<>();

            map.put(Values.USERNAME, tf_username.getText());
            map.put(Values.PASSWORD, tf_password.getText());

            Message message = new Message(MessageType.LOGIN, (HashMap<String, String>) map);
            System.out.println(message);
            communication.write(message);
            System.out.println("message "+ message);

            Message input = communication.read();

            System.out.println(input);
            if (input != null && input.getType() == MessageType.LOGIN && input.getMapContent().get(Values.RESPONSE).equals(Values.OK)) {
                Navigation.getInstance().loadScreen("menu");
                return;
            }
            // TODO: 14/07/17 wrong credentials

        }
    }

    @FXML
    void onRegister(ActionEvent event) {
        lbl_logininfo.setVisible(false);
        lbl_username.setVisible(false);
        lbl_password.setVisible(false);
        Navigation.getInstance().loadScreen("register");
    }

    private boolean emptyField() {
        boolean fieldEmpty = false;

        if (tf_username.getText().length() == 0) {

            setText(lbl_username, "(*Required Field)   ");

            fieldEmpty = true;
        }
        if (tf_password.getText().length() == 0) {

            setText(lbl_password, "(*Required Field)   ");
            fieldEmpty = true;
        }
        return fieldEmpty;
    }

    private <T extends Labeled> void setText(T type, String message) {
        type.setText(message);
        type.setVisible(true);
    }
}
