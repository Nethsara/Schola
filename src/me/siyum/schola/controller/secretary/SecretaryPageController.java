package me.siyum.schola.controller.secretary;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.dto.EmployeeDTO;
import me.siyum.schola.util.Env;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class SecretaryPageController {
    private final EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
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
    public Circle circleImg1;

    public void initialize() throws IOException {
        setData();
        Navigation.navigate(Routes.SECRETARY_HOME, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(dashboardButton, dashboardIcon);
    }

    private void setData() {
        try {
            Env.user = employeeBO.getEmployeeByID(employeeBO.getIDByToken(Env.token, "secretary"));
            EmployeeDTO s = (EmployeeDTO) Env.user;
            Blob data = s.getImage();
            Image im = new Image(data.getBinaryStream());
            circleImg.setFill(new ImagePattern(im));
            circleImg1.setFill(new ImagePattern(im));
            Navigation.navigate(Routes.SECRETARY_HOME, mainPane);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    public void logout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, actionEvent);
    }

    public void btnIncomes(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SECRETARY_FEE, mainPane);
    }
}
