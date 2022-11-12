package me.siyum.schola.controller;

import javafx.event.ActionEvent;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;

import java.io.IOException;

public class LoginPageFormController {
    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONIST, actionEvent);
    }
}
