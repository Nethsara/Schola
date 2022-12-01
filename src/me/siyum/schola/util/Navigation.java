package me.siyum.schola.util;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static Stage stage;
    private static AnchorPane pane;

    public static void navigate(Routes routes, AnchorPane anchorPane) throws IOException {
        switch (routes) {
            case ADMIN_STUDENTS:
                setPanel("admin/AdminStudentPage", anchorPane);
                break;
            case ADMIN_MANAGEMENT:
                setPanel("admin/AdminManagement", anchorPane);
                break;
            case ADMIN_EMPLOYEE:
                setPanel("admin/AdminEmployeeManager", anchorPane);
                break;
            case ADMIN_HOME:
                setPanel("admin/AdminDashboard", anchorPane);
                break;
            case ADMIN_ANOUNCEMENTS:
                setPanel("admin/AdminAnnouncements", anchorPane);
                break;
            case ADMIN_INCOME:
                setPanel("admin/AdminIncomeReports", anchorPane);
                break;
            case ADMIN_SETTINGS:
                setPanel("admin/AdminSettings", anchorPane);
                break;
            case RECEPTIONIST_STUDENTS:
                setPanel("receptionist/ReceptionistStudents", anchorPane);
                break;
            case RECEPTIONIST_SALARY:
                setPanel("receptionist/ReceptionistSalary", anchorPane);
                break;
            case SECRETARY_HOME:
                setPanel("secretary/SecretaryDashboard", anchorPane);
                break;
            case SECRETARY_PAYMENTS:
                setPanel("secretary/SecretaryPayments", anchorPane);
                break;
            case SECRETARY_APPROVAL:
                setPanel("secretary/SecretaryApprovals", anchorPane);
                break;
            case SECRETARY_CLASSES:
                setPanel("secretary/SecretaryClasses", anchorPane);
                break;
            case SECRETARY_STUDENTS:
                setPanel("secretary/SecretaryStudents", anchorPane);
                break;
            case LECTURER_STUDENTS:
                setPanel("lecturers/LectureresStudents", anchorPane);
                break;
            case LECTURER_DASHBOARD:
                setPanel("lecturers/LectureresDashboard", anchorPane);
                break;
            case LECTURER_EXAM:
                setPanel("lecturers/LectureresExams", anchorPane);
                break;
            case LECTURER_ATTENDANCE:
                setPanel("lecturers/LecturerAttendance", anchorPane);
                break;
            case LECTURER_SALARY:
                setPanel("lecturers/LecturerSalary", anchorPane);
                break;
            case LECTURER_CLASS:
                setPanel("lecturers/LectureresClasses", anchorPane);
                break;
            case STUDENT_ANNOUNCEMENT:
                setPanel("students/StudentsAnnouncments", anchorPane);
                break;
            case STUDENT_EXAMS:
                setPanel("students/StudentExamPage", anchorPane);
                break;
            case STUDENT_DASHBOARD:
                setPanel("students/StudentDashboard", anchorPane);
                break;
            case RECEPTIONIST_HOME:
                setPanel("receptionist/ReceptionistDashboard", anchorPane);
                break;
            case STUDENT_CLASSES:
                setPanel("students/StudentClasses", anchorPane);
                break;
            case RECEPTIONIST_ATTENDANCE:
                setPanel("receptionist/ReceptionistAttendance", anchorPane);
                break;
            case LECTURER_HOMEWORK:
                setPanel("lecturers/LectureresHomeWorks", anchorPane);
                break;
            case STUDENT_HOMEWORKS:
                setPanel("students/StudentHWPage", anchorPane);
                break;
            case STUDENT_FEE:
                setPanel("students/StudentsPayments", anchorPane);
                break;
        }

    }

    private static void setPanel(String s, AnchorPane anchorPane) throws IOException {
        System.out.println(s + " panel " + anchorPane + " anchor pane");
        Parent parent = FXMLLoader.load((Navigation.class.getResource("../view/" + s + ".fxml")));
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(parent);
    }

    public static void navigate(Routes routes, Event event) throws IOException {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
        switch (routes) {
            case RECEPTIONIST:
                initUI("headers/ReceptionistPage");
                break;
            case ADMIN:
                initUI("headers/AdminPage");
                break;
            case STUDENT:
                initUI("headers/StudentsPage");
                break;
            case SECRETARY:
                initUI("headers/SecretaryPage");
                break;
            case LOGIN:
                Env.user = null;
                Env.token = "";
                initUI("LoginPageForm");
                break;
            case LECTURER:
                initUI("headers/LecturerPage");
                break;

        }
    }

    public static void initUI(String location) throws IOException {
        Scene scene = null;
        scene = new Scene(FXMLLoader.load(Navigation.class.getResource("../view/" + location + ".fxml")));
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
