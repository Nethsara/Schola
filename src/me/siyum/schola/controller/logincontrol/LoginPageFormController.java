package me.siyum.schola.controller.logincontrol;

import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.siyum.schola.util.ExamMarking;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
            ArrayList<String> strings = LoginController.loginValidate(txtEmail.getText(), txtPassword.getText());
            boolean b = LoginController.writeToken(strings.get(1), strings.get(0));
            System.out.println(b);
            if (b) {
                System.out.println("Login success!");
                if (strings.get(0).equalsIgnoreCase("admin")) {
                    Navigation.navigate(Routes.ADMIN, actionEvent);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Class Not Found");
        }
    }
}
