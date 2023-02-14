package me.siyum.schola.controller.students;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.util.Env;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class StudentsPageController {
    public Label lblNotifications;
    public Circle circleImg;
    public AnchorPane mainPane;
    public Circle circleImgLeft;
    public JFXButton btnDash;
    public FontAwesomeIconView iconDash;
    public JFXButton btnExam;
    public FontAwesomeIconView iconExam;
    public JFXButton btnClass;
    public FontAwesomeIconView iconClass;
    public JFXButton btnHomeWork;
    public FontAwesomeIconView iconHomeWork;
    public JFXButton notificationBtn;
    public FontAwesomeIconView notificationIcon;
    public JFXButton btnSalary;
    public FontAwesomeIconView iconSalary;
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOTypes.STUDENT);

    public void initialize() throws IOException {
        setButtonClorsNormal();
        Navigation.navigate(Routes.STUDENT_DASHBOARD, mainPane);
        setBtnColorGreen(btnDash, iconDash);
        setData();
    }

    private void setData() {
        try {
            Env.user = studentBO.retrieveStudent(studentBO.getStudentByToken(Env.token));
            StudentDTO s = (StudentDTO) Env.user;
            Blob data = s.getImage();
            Image im = new Image(data.getBinaryStream());
            circleImg.setFill(new ImagePattern(im));
            circleImgLeft.setFill(new ImagePattern(im));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void notificationsPane() throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(notificationBtn, notificationIcon);
        Navigation.navigate(Routes.STUDENT_ANNOUNCEMENT, mainPane);
    }


    public void studentExam() throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(btnExam, iconExam);
        Navigation.navigate(Routes.STUDENT_EXAMS, mainPane);
    }

    public void classes() throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(btnClass, iconClass);
        Navigation.navigate(Routes.STUDENT_CLASSES, mainPane);
    }

    public void billing(ActionEvent actionEvent) throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(btnSalary, iconSalary);
        Navigation.navigate(Routes.STUDENT_FEE, mainPane);
    }

    public void homeWork() throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(btnHomeWork, iconHomeWork);
        Navigation.navigate(Routes.STUDENT_HOMEWORKS, mainPane);
    }

    public void homePage() throws IOException {
        setBtnColorGreen(btnDash, iconDash);
        setData();
        Navigation.navigate(Routes.STUDENT_DASHBOARD, mainPane);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, actionEvent);
    }

    public void notifications(ActionEvent actionEvent) throws IOException {
        setButtonClorsNormal();
        setBtnColorGreen(notificationBtn, notificationIcon);
        Navigation.navigate(Routes.STUDENT_NOTIFICATIONS, mainPane);
    }

    private void setButtonClorsNormal() {
        Button[] btns = {btnDash, btnClass, btnExam, btnHomeWork, btnSalary, notificationBtn};
        for (Button bt : btns
        ) {
            bt.setStyle("-fx-fill:#95a0a9");
        }

        FontAwesomeIconView[] iconViews = {iconSalary, notificationIcon, iconHomeWork, iconClass, iconExam, iconDash};
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
