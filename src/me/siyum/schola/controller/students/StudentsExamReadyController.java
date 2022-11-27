package me.siyum.schola.controller.students;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import me.siyum.schola.util.ExamMarking;

import java.io.IOException;

public class StudentsExamReadyController {

    public Label lblExmID;
    public Label lblStID;


    public void startExam(ActionEvent actionEvent) {
        ExamMarking.mark = 0;
        ExamMarking.currentQ = 1;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(".././../view/students/StudentsExamQuestions.fxml"));
            Parent parent = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setData(String exmID, String stID) {
        lblExmID.setText(exmID);
        lblStID.setText(stID);
        ExamMarking.examID = exmID;
        ExamMarking.stID = stID;
    }
}
