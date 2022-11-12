package me.siyum.schola.controller;

import javafx.event.ActionEvent;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class LoginPageFormController {
    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        try {
            Navigation.navigate(Routes.RECEPTIONIST, actionEvent, LoginController.getToken());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
