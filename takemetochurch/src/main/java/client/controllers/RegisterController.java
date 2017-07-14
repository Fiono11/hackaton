package client.controllers;

import client.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import utils.Verification;

import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void backScreen(ActionEvent event) {
        Navigation.getInstance().back();
    }

    @FXML
    void onRegister(ActionEvent event) {

        Verification.cleanRegisterMsg(lb_username_reg, lb_password_reg, lb_firstname, lb_lastname, lbl_phone, lbl_email);

        if (!emptyField()) {
            checkEmail = Verification.checkEmail(tf_email);

            if (!checkEmail) {
                setText(lbl_email, "(* Invalid email)    ");
            }
            checkPass = Verification.checkPassword(tf_password);
            if (!checkPass) {
                setText(lb_password_reg, "(* Minimum of 8 characters containing at least\n 1 number," +
                        " 1 lower case and 1 upper case letter)  ");
            }

            checkPhone = Verification.checkPhone(tf_phone);
            if (!checkPhone) {
                setText(lbl_phone, "(* Invalid phone number)    ");
            }

        }


        // pedir ao serviço para enviar o registo
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

            setText(lb_lastname, "(* Required Field)   ");
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
}
