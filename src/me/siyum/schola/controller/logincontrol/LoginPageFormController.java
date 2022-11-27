package me.siyum.schola.controller.logincontrol;

import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.siyum.schola.util.ExamMarking;

import java.io.IOException;
import java.sql.SQLException;

public class LoginPageFormController {
    public TextField txtEmail;
    public PasswordField txtPassword;
    public Label passwordStatus;

    public void initialize() {
        setData();
    }

    private void setData() {
        try {
            ExamMarking.scholaReload();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loginOnAction(Event actionEvent) throws IOException {
        try {
            int loginStatus = LoginController.loginValidate(txtEmail.getText(), txtPassword.getText());
            if (loginStatus == -1) {
                passwordStatus.setText("Username or Password Incorrect!");
            } else if (loginStatus == 2) {
                LoginController.login("Receptionist", actionEvent);
            } else {
            }
        } catch (SQLException e) {
            passwordStatus.setText("Username or Password Incorrect!");
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Class Not Found");
        }
    }
}
