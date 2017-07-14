package client.controllers;

import client.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import client.utils.Verification;
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
    private TextField tf_firstname;

    @FXML
    private Label lb_firstname;

    @FXML
    private TextField tf_lastname;

    @FXML
    private Label lb_lastname;

    @FXML
    private PasswordField tf_password;

    @FXML
    private Label lb_password_reg;

    @FXML
    private TextField tf_phone;

    @FXML
    private Label lbl_phone;

    @FXML
    private TextField tf_email;

    @FXML
    private Label lbl_email;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnBack;

    private boolean fieldEmpty;
    private boolean checkEmail;
    private boolean checkPass;
    private boolean checkPhone;
    private Communication communication;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        communication = Navigation.getInstance().getCommunication();
    }

    @FXML
    void backScreen(ActionEvent event) {
        Navigation.getInstance().back();
    }

    @FXML
    void onRegister(ActionEvent event) {

        Verification.cleanRegisterMsg(lb_username_reg, lb_password_reg, lb_firstname, lb_lastname, lbl_phone, lbl_email);
        int i = 0;
        if (!emptyField()) {

            checkEmail = Verification.checkEmail(tf_email);

            if (!checkEmail) {
                setText(lbl_email, "(* Invalid email)    ");
                i++;
            }
            checkPass = Verification.checkPassword(tf_password);
            if (!checkPass) {
                setText(lb_password_reg, "(* Minimum of 8 characters containing at least\n 1 number," +
                        " 1 lower case and 1 upper case letter)  ");
                i++;
            }

            checkPhone = Verification.checkPhone(tf_phone);
            if (!checkPhone) {
                setText(lbl_phone, "(* Invalid phone number)    ");
                i++;
            }

            if (i != 0){
                return;
            }

            Map<String, String> map = new HashMap<>();
            map.put(Values.USERNAME, tf_username.getText());
            map.put(Values.FIRST_NAME, tf_firstname.getText());
            map.put(Values.LAST_NAME, tf_lastname.getText());
            map.put(Values.PASSWORD, tf_password.getText());
            map.put(Values.PHONE, tf_phone.getText());
            map.put(Values.EMAIL, tf_email.getText());
            Message message = new Message(MessageType.REGISTRY, (HashMap<String, String>) map);
            communication.write(message);

            Message message1 = communication.read();
            if(message1.getMapContent().get(Values.RESPONSE).equals(Values.OK)){
                Navigation.getInstance().back();
            }
            else {
                lbl_email.setText("Register Failed.   ");
                lbl_email.setVisible(true);
                lb_username_reg.setText("Username already exists   ");
                lb_username_reg.setVisible(true);
            }

            //playSound();
        }
    }

    private boolean emptyField() {
        fieldEmpty = false;
        if (tf_username.getText().length() == 0) {

            setText(lb_username_reg, "(* Required Field)   ");
            fieldEmpty = true;
        }
        if (tf_password.getText().length() == 0) {

            setText(lb_password_reg, "(* Required Field)   ");
            fieldEmpty = true;
        }
        if (tf_firstname.getText().length() == 0) {

            setText(lb_firstname, "(* Required Field)   ");
            fieldEmpty = true;
        }

        if (tf_lastname.getText().length() == 0) {

            setText(lb_lastname, "(* Required Field)");
            fieldEmpty = true;
        }

        if (tf_phone.getText().length() == 0) {

            setText(lbl_phone, "(* Required Field)   ");
            fieldEmpty = true;
        }

        if (tf_email.getText().length() == 0) {

            setText(lbl_email, "(* Required Field)  ");
            fieldEmpty = true;
        }
        return fieldEmpty;
    }

    private <T extends Labeled> void setText(T type, String message) {
        type.setText(message);
        type.setVisible(true);
    }

    private void playSound() {
        new MediaPlayer(new Media(getClass().getResource("/sound/takemetochurch.mp3").toString())).play();
    }
}
