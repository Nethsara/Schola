package me.siyum.schola.controller.lecturers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;

import java.io.IOException;

public class LecturersPageController {
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
        Navigation.navigate(Routes.LECTURER_DASHBOARD, mainPane);
    }

    public void homePage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LECTURER_DASHBOARD, mainPane);
    }

    public void studentsPage(ActionEvent actionEvent) throws IOException {
        System.out.println("studet");
        Navigation.navigate(Routes.LECTURER_STUDENTS, mainPane);
    }

    public void examsPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LECTURER_EXAM, mainPane);
    }

    public void classPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LECTURER_CLASS, mainPane);
    }

    public void salaryPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LECTURER_SALARY, mainPane);

    }

    public void attendancePage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LECTURER_ATTENDANCE, mainPane);
    }

    public void homeWorks(ActionEvent actionEvent) {
    }

    public void logout(MouseEvent mouseEvent) {
    }
}
