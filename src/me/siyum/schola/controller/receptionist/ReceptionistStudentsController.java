package me.siyum.schola.controller.receptionist;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReceptionistStudentsController {
    public void newStudent(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load((getClass().getResource(".././../view/students/StudentForm.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
