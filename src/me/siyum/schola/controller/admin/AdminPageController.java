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
    public JFXButton btnManagement;
    public FontAwesomeIconView iconManagement;

    public void initialize() throws IOException {
        Navigation.navigate(Routes.ADMIN_HOME, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(dashboardButtton, dashboardIcon);
        Image im = new javafx.scene.image.Image("me/siyum/schola/assets/images/admin.jpg", false);
        circleImgLeft.setFill(new ImagePattern(im));
    }

    public void homePage() throws IOException {
        Navigation.navigate(Routes.ADMIN_HOME, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(dashboardButtton, dashboardIcon);
    }

    public void studentsPage() throws IOException {
        Navigation.navigate(Routes.ADMIN_STUDENTS, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(studentsButton, studentsIcon);
    }

    public void employersPage() throws IOException {
        Navigation.navigate(Routes.ADMIN_EMPLOYEE, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(employerButton, iconEmployee);
    }

    public void announcementPage() throws IOException {
        Navigation.navigate(Routes.ADMIN_ANOUNCEMENTS, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(btnAnnouncements, iconAnnoun);
    }

    public void incomesPage() throws IOException {
        Navigation.navigate(Routes.ADMIN_INCOME, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(btnIncome, iconIncome);
    }

    public void settingsPage() throws IOException {
        Navigation.navigate(Routes.ADMIN_SETTINGS, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(btnSettings, iconSettings);
    }


    private void setButtonClorsNormal() {
        Button[] btns = {btnAnnouncements, btnIncome, btnSettings, dashboardButtton, studentsButton, employerButton, employerButton, btnManagement};
        for (Button bt : btns
        ) {
            bt.setStyle("-fx-fill:#95a0a9");
        }

        FontAwesomeIconView[] iconViews = {iconAnnoun, iconIncome, iconSettings, iconEmployee, studentsIcon, dashboardIcon, iconManagement};
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
        Navigation.navigate(Routes.LOGIN, actionEvent);
    }

    public void management() throws IOException {
        setButtonClorsNormal();
        Navigation.navigate(Routes.ADMIN_MANAGEMENT, mainPane);
        setBtnColorGreen(btnManagement, iconManagement);
    }
}
