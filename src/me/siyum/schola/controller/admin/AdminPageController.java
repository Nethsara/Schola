package me.siyum.schola.controller.admin;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
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
    public Circle circleImgLeft;
    public JFXButton dashboardButtton;
    public FontAwesomeIconView dashboardIcon;
    public JFXButton studentsButton;
    public FontAwesomeIconView studentsIcon;
    public JFXButton employerButton;
    public FontAwesomeIconView iconEmployee;
    public JFXButton btnAnnouncements;
    public FontAwesomeIconView iconAnnoun;
    public JFXButton btnIncome;
    public FontAwesomeIconView iconIncome;
    public JFXButton btnSettings;
    public FontAwesomeIconView iconSettings;

    public void initialize() throws IOException {
        Image im = new javafx.scene.image.Image("me/siyum/schola/assets/images/admin.png", false);
        circleImg.setFill(new ImagePattern(im));
        circleImgLeft.setFill(new ImagePattern(im));
        Navigation.navigate(Routes.ADMIN_HOME, mainPane);
    }

    public void homePage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_HOME, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(dashboardButtton, dashboardIcon);
    }

    public void studentsPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_STUDENTS, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(studentsButton, studentsIcon);
    }

    public void employersPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_EMPLOYEE, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(employerButton, iconEmployee);
    }

    public void announcementPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_ANOUNCEMENTS, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(btnAnnouncements, iconAnnoun);
    }

    public void incomesPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_INCOME, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(btnIncome, iconIncome);
    }

    public void settingsPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_SETTINGS, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(btnSettings, iconSettings);
    }


    private void setButtonClorsNormal() {
        Button[] btns = {btnAnnouncements, btnIncome, btnSettings, dashboardButtton, studentsButton, employerButton, employerButton};
        for (Button bt : btns
        ) {
            bt.setStyle("-fx-fill:#95a0a9");
        }

        FontAwesomeIconView[] iconViews = {iconAnnoun, iconIncome, iconSettings, iconEmployee, studentsIcon, dashboardIcon};
        for (FontAwesomeIconView icn : iconViews
        ) {
            icn.setStyle("-fx-fill:#95a0a9");
        }
    }

    private void setBtnColorGreen(Button btn, FontAwesomeIconView icon) {
        btn.setStyle("-fx-text-fill:#1eb569");
        icon.setStyle("-fx-fill:#1eb569");
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        System.out.println("Logout");
        Navigation.navigate(Routes.LOGIN, actionEvent);
    }
}
