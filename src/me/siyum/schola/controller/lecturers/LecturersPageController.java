package me.siyum.schola.controller.lecturers;

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

public class LecturersPageController {
    private final EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    public AnchorPane mainPane;
    public Circle circleImg;
    public JFXButton btnDash;
    public FontAwesomeIconView iconDash;
    public JFXButton btnStudent;
    public FontAwesomeIconView iconStudents;
    public JFXButton btnExam;
    public FontAwesomeIconView iconExam;
    public JFXButton btnClass;
    public FontAwesomeIconView iconClass;
    public JFXButton btnAttendance;
    public FontAwesomeIconView iconAttendance;
    public JFXButton btnHomeWork;
    public FontAwesomeIconView iconHomeWork;
    public JFXButton btnSalary;
    public FontAwesomeIconView iconSalary;
    public Circle circleImgLeft;

    public void initialize() throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(btnDash, iconDash);
        setData();
    }

    private void setData() {
        try {
            Env.user = employeeBO.getEmployeeByID(employeeBO.getIDByToken(Env.token, "lecturer"));
            EmployeeDTO s = (EmployeeDTO) Env.user;
            Blob data = s.getImage();
            Image im = new Image(data.getBinaryStream());
            circleImgLeft.setFill(new ImagePattern(im));
            circleImg.setFill(new ImagePattern(im));
            Navigation.navigate(Routes.LECTURER_DASHBOARD, mainPane);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void homePage() throws IOException {
        Navigation.navigate(Routes.LECTURER_DASHBOARD, mainPane);
        setButtonClorsNormal();
        setBtnColorGreen(btnDash, iconDash);
    }

    public void studentsPage() throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(btnStudent, iconStudents);
        Navigation.navigate(Routes.LECTURER_STUDENTS, mainPane);
    }

    public void examsPage() throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(btnExam, iconExam);
        Navigation.navigate(Routes.LECTURER_EXAM, mainPane);
    }

    public void classPage() throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(btnClass, iconClass);
        Navigation.navigate(Routes.LECTURER_CLASS, mainPane);
    }

    public void salaryPage() throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(btnSalary, iconSalary);
        Navigation.navigate(Routes.LECTURER_SALARY, mainPane);
    }

    public void attendancePage() throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(btnAttendance, iconAttendance);
        Navigation.navigate(Routes.LECTURER_ATTENDANCE, mainPane);
    }

    public void homeWorks() throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(btnHomeWork, iconHomeWork);
        Navigation.navigate(Routes.LECTURER_HOMEWORK, mainPane);
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, actionEvent);
    }

    private void setButtonClorsNormal() {
        Button[] btns = {btnDash, btnClass, btnExam, btnHomeWork, btnSalary, btnAttendance, btnStudent};
        for (Button bt : btns
        ) {
            bt.setStyle("-fx-fill:#95a0a9");
        }

        FontAwesomeIconView[] iconViews = {iconSalary, iconAttendance, iconHomeWork, iconClass, iconExam, iconDash, iconStudents};
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
