package me.siyum.schola.controller.students;

import javafx.event.ActionEvent;
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
    StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);

    public void initialize() throws IOException {
        Navigation.navigate(Routes.STUDENT_DASHBOARD, mainPane);
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
        Navigation.navigate(Routes.STUDENT_ANNOUNCEMENT, mainPane);
    }


    public void studentExam() throws IOException {
        Navigation.navigate(Routes.STUDENT_EXAMS, mainPane);
    }

    public void classes() throws IOException {
        Navigation.navigate(Routes.STUDENT_CLASSES, mainPane);
    }

    public void billing(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT_FEE, mainPane);
    }

    public void homeWork() throws IOException {
        Navigation.navigate(Routes.STUDENT_HOMEWORKS, mainPane);
    }

    public void homePage() throws IOException {
        Navigation.navigate(Routes.STUDENT_DASHBOARD, mainPane);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, actionEvent);
    }
}
