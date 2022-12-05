package me.siyum.schola.controller.logincontrol;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.UsersBO;
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

    public void initialize() throws SQLException, ClassNotFoundException {
        setData();
        //Mailing.sendMail("");
    }

    private void setData() throws SQLException, ClassNotFoundException {
        UsersBO usersBO = BOFactory.getInstance().getBO(BOTypes.USERS);
        usersBO.getLastID();
        try {
            ExamMarking.scholaReload();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loginOnAction(Event actionEvent) throws IOException {
        try {
            ArrayList<String> strings = LoginController.loginValidate(txtEmail.getText(), txtPassword.getText());
            if (strings == null) {
                new Alert(Alert.AlertType.WARNING, "Enter correct data").show();
            } else {
                boolean b = LoginController.writeToken(strings.get(1), strings.get(0));
                if (b) {
                    if (strings.get(0).equalsIgnoreCase("admin")) {
                        Navigation.navigate(Routes.ADMIN, actionEvent);
                    } else if (strings.get(0).equalsIgnoreCase("lecturer")) {
                        Navigation.navigate(Routes.LECTURER, actionEvent);
                    } else if (strings.get(0).equalsIgnoreCase("secretary")) {
                        Navigation.navigate(Routes.SECRETARY, actionEvent);
                    } else if (strings.get(0).equalsIgnoreCase("receptionist")) {
                        Navigation.navigate(Routes.RECEPTIONIST, actionEvent);
                    } else if (strings.get(0).equalsIgnoreCase("student")) {
                        Navigation.navigate(Routes.STUDENT, actionEvent);
                    }
                    //else if (strings.get(0).equalsIgnoreCase("staff")) {
                    //Navigation.navigate(Routes.ADMIN, actionEvent);
                    // }
                }
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "FIll the data!").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Class Not Found");
        }
    }

    public void login(ActionEvent actionEvent) throws IOException {
        loginOnAction(actionEvent);
    }
}
