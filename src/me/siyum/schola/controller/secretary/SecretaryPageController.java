package me.siyum.schola.controller.secretary;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;

import java.io.IOException;

public class SecretaryPageController {
    public Circle circleImg;
    public AnchorPane mainPane;
    public JFXButton dashboardButton;
    public FontAwesomeIconView dashboardIcon;
    public JFXButton studentsButton;
    public FontAwesomeIconView studentsIcon;
    public JFXButton btnAnnouncements;
    public FontAwesomeIconView iconAnon;
    public JFXButton btnIncome;
    public FontAwesomeIconView iconIncome;
    public JFXButton btnSettings;
    public FontAwesomeIconView iconSettings;
    public Circle circleImgLeft;

    public void initialize() throws IOException {
        Navigation.navigate(Routes.SECRETARY_HOME, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(dashboardButton, dashboardIcon);
    }

    public void secretaryHome() throws IOException {
        Navigation.navigate(Routes.SECRETARY_HOME, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(dashboardButton, dashboardIcon);
    }

    public void secretaryStudents() throws IOException {
        Navigation.navigate(Routes.SECRETARY_STUDENTS, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(studentsButton, studentsIcon);
    }

    public void secretaryApproval() throws IOException {
        Navigation.navigate(Routes.SECRETARY_APPROVAL, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(btnAnnouncements, iconAnon);
    }

    public void secretaryPayments() throws IOException {
        Navigation.navigate(Routes.SECRETARY_PAYMENTS, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(btnIncome, iconIncome);
    }

    public void secretatyClasses() throws IOException {
        Navigation.navigate(Routes.SECRETARY_CLASSES, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(btnSettings, iconSettings);
    }

    public void logout(MouseEvent mouseEvent) {
    }

    private void setButtonClorsNormal() {
        Button[] btns = {btnAnnouncements, btnIncome, btnSettings, dashboardButton, studentsButton};
        for (Button bt : btns
        ) {
            bt.setStyle("-fx-fill:#95a0a9");
        }

        FontAwesomeIconView[] iconViews = {iconAnon, iconIncome, iconSettings, studentsIcon, dashboardIcon};
        for (FontAwesomeIconView icn : iconViews
        ) {
            icn.setStyle("-fx-fill:#95a0a9");
        }
    }

    private void setBtnColorGreen(Button btn, FontAwesomeIconView icon) {
        btn.setStyle("-fx-text-fill:#1eb569");
        icon.setStyle("-fx-fill:#1eb569");
    }
}
