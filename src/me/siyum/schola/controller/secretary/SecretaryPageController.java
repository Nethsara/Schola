package me.siyum.schola.controller.secretary;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;

import java.io.IOException;

public class SecretaryPageController {
    public Circle circleImg;
    public AnchorPane mainPane;
    public void initialize() throws IOException {
        Navigation.navigate(Routes.SECRETARY_HOME, mainPane);
    }

    public void secretaryHome() throws IOException {
        Navigation.navigate(Routes.SECRETARY_HOME, mainPane);
    }

    public void secretaryStudents() throws IOException {
        Navigation.navigate(Routes.SECRETARY_STUDENTS, mainPane);
    }

    public void secretaryApproval() throws IOException {
        Navigation.navigate(Routes.SECRETARY_APPROVAL, mainPane);
    }

    public void secretaryPayments() throws IOException {
        Navigation.navigate(Routes.SECRETARY_PAYMENTS, mainPane);
    }

    public void secretatyClasses() throws IOException {
        Navigation.navigate(Routes.SECRETARY_CLASSES, mainPane);
    }
}
