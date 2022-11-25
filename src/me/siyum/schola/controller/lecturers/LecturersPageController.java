package me.siyum.schola.controller.lecturers;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;

import java.io.IOException;

public class LecturersPageController {
    public AnchorPane mainPane;

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

    public void reportsPage(ActionEvent actionEvent) {
    }
}
