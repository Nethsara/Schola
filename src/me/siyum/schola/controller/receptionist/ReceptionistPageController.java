package me.siyum.schola.controller.receptionist;

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

public class ReceptionistPageController {

    public AnchorPane receptionistPane;
    public AnchorPane mainPane;
    public Circle circleImgLeft;
    public JFXButton dashboardButtton;
    public FontAwesomeIconView dashboardIcon;
    public JFXButton studentsButton;
    public FontAwesomeIconView studentsIcon;
    public JFXButton attendanceButton;
    public JFXButton salaryButton;
    public FontAwesomeIconView iconAttendance;
    public FontAwesomeIconView iconSalary;

    EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);

    public void initialize() throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(dashboardButtton, dashboardIcon);
        Navigation.navigate(Routes.RECEPTIONIST_HOME, mainPane);
        setData();
    }

    private void setData() {
        try {
            Env.user = employeeBO.getEmployeeByID(employeeBO.getIDByToken(Env.token, "receptionist"));
            EmployeeDTO s = (EmployeeDTO) Env.user;
            Blob data = s.getImage();
            Image im = new Image(data.getBinaryStream());
            circleImgLeft.setFill(new ImagePattern(im));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void studentsPageLoad(ActionEvent actionEvent) throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(studentsButton, studentsIcon);
        Navigation.navigate(Routes.RECEPTIONIST_STUDENTS, mainPane);
    }

    public void salaryPage(ActionEvent actionEvent) throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(salaryButton, iconSalary);
        Navigation.navigate(Routes.RECEPTIONIST_SALARY, mainPane);
    }

    public void receptionistHome(ActionEvent actionEvent) throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(dashboardButtton, dashboardIcon);
        Navigation.navigate(Routes.RECEPTIONIST_HOME, mainPane);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, actionEvent);
    }

    public void attendancePage(ActionEvent actionEvent) throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(attendanceButton, iconAttendance);
        Navigation.navigate(Routes.RECEPTIONIST_ATTENDANCE, mainPane);
    }

    private void setButtonClorsNormal() {
        Button[] btns = {dashboardButtton, studentsButton, attendanceButton, salaryButton};
        for (Button bt : btns
        ) {
            bt.setStyle("-fx-fill:#95a0a9");
        }

        FontAwesomeIconView[] iconViews = {dashboardIcon, iconAttendance, iconSalary, studentsIcon};
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
