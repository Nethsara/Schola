package me.siyum.schola.controller.receptionist;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;

import java.io.IOException;

public class ReceptionistPageController {

    public AnchorPane receptionistPane;
    public AnchorPane mainPane;

    public void initialize() throws IOException {
        Navigation.navigate(Routes.RECEPTIONIST_HOME, mainPane);
    }


    public void studentsPageLoad(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONIST_STUDENTS, mainPane);
    }

    public void salaryPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONIST_SALARY, mainPane);
    }

    public void receptionistHome(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONIST_HOME, mainPane);
    }

    public void logout(ActionEvent actionEvent) {
    }

    public void attendancePage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONIST_ATTENDANCE, mainPane);
    }
}
