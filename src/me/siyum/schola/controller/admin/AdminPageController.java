package me.siyum.schola.controller.admin;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;

import java.io.IOException;

public class AdminPageController {
    public Circle circleImg;
    public AnchorPane mainPane;

    public void initialize() throws IOException {
        Image im = new javafx.scene.image.Image("me/siyum/schola/assets/images/admin.png", false);
        circleImg.setFill(new ImagePattern(im));
        Navigation.navigate(Routes.ADMIN_HOME, mainPane);
    }

    public void homePage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_HOME, mainPane);
    }

    public void studentsPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_STUDENTS, mainPane);
    }

    public void employersPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_EMPLOYEE, mainPane);
    }

    public void announcementPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_ANOUNCEMENTS, mainPane);
    }

    public void incomesPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_INCOME, mainPane);
    }

    public void settingsPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_SETTINGS, mainPane);
    }

}
