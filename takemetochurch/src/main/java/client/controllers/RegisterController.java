package client.controllers;

import client.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import client.utils.Verification;
import shared.Message;
import shared.MessageType;
import shared.Values;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by codecadet Helder Matos on 13/07/17.
 */
public class RegisterController implements Initializable {

    @FXML
    private GridPane name;

    @FXML
    private TextField tf_username;

    @FXML
    private Label lb_username_reg;

    @FXML
    private PasswordField tf_firstname;

    @FXML
    private Label lb_firstname;

    @FXML
    private TextField tf_lastname;

    @FXML
    private Label lb_lastname;

    @FXML
    private TextField tf_password;

    @FXML
    private Label lb_password_reg;

    @FXML
    private TextField tf_email;

    @FXML
    private Label lb_email;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnBack;

    private boolean fieldEmpty;
    private boolean checkEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void backScreen(ActionEvent event) {
        Navigation.getInstance().back();
    }

    @FXML
    void onRegister(ActionEvent event) {

        Verification.cleanRegisterMsg(lb_username_reg, lb_password_reg, lb_firstname, lb_lastname, lb_email);

        if (!emptyField()) {
            Map<String, String> map = new HashMap<>();
            map.put(Values.USERNAME, tf_username.getText());
            map.put(Values.PASSWORD, tf_password.getText());
            map.put(Values.EMAIL, tf_email.getText());
            map.put(Values.FIRSTNAME, tf_firstname.getText());
            map.put(Values.LASTNAME, tf_lastname.getText());
            Message message = new Message(MessageType.REGISTRY, (HashMap<String, String>) map);
            Navigation.getInstance().getCommunication().write(message);
        }
    }

    private boolean emptyField() {
        fieldEmpty = false;
        if (tf_username.getText().length() == 0) {

            setText(lb_username_reg, "(* Required Field)");
            fieldEmpty = true;
        }
        if (tf_password.getText().length() == 0) {

            setText(lb_password_reg, "(* Required Field)");
            fieldEmpty = true;
        }
        if (tf_firstname.getText().length() == 0) {

            setText(lb_firstname, "(* Required Field)");
            fieldEmpty = true;
        }

        if (tf_lastname.getText().length() == 0) {

            setText(lb_lastname, "(* Required Field)");
            fieldEmpty = true;
        }

        if (tf_email.getText().length() == 0) {

            setText(lb_email, "(* Required Field)");
            fieldEmpty = true;
        }
        return fieldEmpty;
    }

    private <T extends Labeled> void setText(T type, String message) {
        type.setText(message);
        type.setVisible(true);
    }
}
