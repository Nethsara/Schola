package me.siyum.schola.controller.students;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;

import java.io.IOException;

public class StudentsPageController {
    public Label lblNotifications;
    public Circle circleImg;
    public AnchorPane mainPane;

    public void initialize() throws IOException {
        Navigation.navigate(Routes.STUDENT_DASHBOARD, mainPane);
    }

    public void notificationsPane(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT_ANNOUNCEMENT, mainPane);
    }

    public void studentHome(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT_DASHBOARD, mainPane);
    }

    public void studentExam(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT_EXAMS, mainPane);
    }

    public void classes(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT_CLASSES, mainPane);
    }

    public void billing(ActionEvent actionEvent) {
    }

    public void homeWork(ActionEvent actionEvent) {
    }
}
