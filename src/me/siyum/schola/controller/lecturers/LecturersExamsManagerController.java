package me.siyum.schola.controller.lecturers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.ExamsBO;
import me.siyum.schola.dto.ExamsDTO;

import java.io.IOException;

public class LecturersExamsManagerController {
    public TextField txtExamID;
    public TextField txtLecID;
    public JFXComboBox cmbBatchID;
    public JFXDatePicker pickerDate;
    ExamsBO examsBO = BOFactory.getInstance().getBO(BOTypes.EXAMS);

    public void offlineExam(ActionEvent actionEvent) {
        try {
            boolean b = saveExam();

            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "Successfully saved").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onlineExam(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(".././../view/lecturers/LectureresExamQuestions.fxml"));
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();

            LecturersExamsQuestionsController rowController = fxmlLoader.getController();
            rowController.setExamID(txtExamID.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean saveExam() throws Exception {
        return examsBO.saveExams(new ExamsDTO(
                txtExamID.getText(),
                pickerDate.getValue(),
                cmbBatchID.getValue().toString(),
                txtLecID.getText()
        ));
    }
}
