package utils;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by codecadet Helder Matos on 13/07/17.
 */
public class Verification {

    public static void cleanRegisterMsg(Label lblUsernameError, Label lblPasswordError, Label lblFirstNameErrorReg, Label lblLastName, Label lblPhoneNumber, Label lblMailErrorReg) {

        lblUsernameError.setVisible(false);
        lblPasswordError.setVisible(false);
        lblFirstNameErrorReg.setVisible(false);
        lblLastName.setVisible(false);
        lblPhoneNumber.setVisible(false);
        lblMailErrorReg.setVisible(false);

}

    public static void cleanLoginMsg(Label lbl_username, Label lbl_password, Label lbl_info) {

        lbl_username.setVisible(false);
        lbl_password.setVisible(false);
        lbl_info.setVisible(false);

    }



    public static boolean checkEmail(TextField email) {
        boolean checkMail = false;

        if (email.getText().matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:" +
                "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")) {

            checkMail = true;
        }

        return checkMail;
    }

    public static boolean checkPassword(PasswordField password) {
        boolean check = false;

        if (password.getText().matches("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])" +
                "(?=.*[A-Z])(?=.*[a-z]).*$")) {

            check = true;
        }

        return check;
    }

    public static boolean checkPhone(TextField tf_phone) {
        boolean checkPhone = false;

        if (tf_phone.getText().matches("9[1236]{1}[0-9]{7}")) {

            checkPhone = true;
        }

        return checkPhone;

    }
}
