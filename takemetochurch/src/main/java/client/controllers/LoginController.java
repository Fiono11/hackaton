package client.controllers;

import client.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.Verification;

public class LoginController {

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
    void onLogin(ActionEvent event) {
        Verification.cleanLoginMsg(lbl_username, lbl_password);

        if (!emptyField()) {

            // TODO: 13/07/17 filled fields send message

            lbl_logininfo.setVisible(false);
        }

        //
    }

    @FXML
    void onRegister(ActionEvent event) {
        Navigation.getInstance().loadScreen("register");
    }

    private boolean emptyField() {
        boolean fieldEmpty = false;

        if (tf_username.getText().length() == 0) {

            setText(lbl_username, "(*Required Field)");
            fieldEmpty = true;
        }
        if (tf_password.getText().length() == 0) {

            setText(lbl_password, "(*Required Field)");
            fieldEmpty = true;
        }
        return fieldEmpty;
    }

    private <T extends Labeled> void setText(T type, String message){
        type.setText(message);
        type.setVisible(true);
    }


}
