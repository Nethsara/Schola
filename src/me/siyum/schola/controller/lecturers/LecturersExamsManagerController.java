package me.siyum.schola.controller.lecturers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.BatchBO;
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.bo.custom.ExamsBO;
import me.siyum.schola.dto.BatchDTO;
import me.siyum.schola.dto.ExamsDTO;
import me.siyum.schola.util.Env;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LecturersExamsManagerController {
    public TextField txtExamID;
    public TextField txtLecID;
    public JFXComboBox<String> cmbBatchID;
    public JFXDatePicker pickerDate;
    ExamsBO examsBO = (ExamsBO) BOFactory.getInstance().getBO(BOTypes.EXAMS);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    BatchBO batchBO = (BatchBO) BOFactory.getInstance().getBO(BOTypes.BATCHES);

    public void initialize() {
        setData();
        pickerDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.isBefore(today));
            }
        });
    }

    public void setData() {
        try {
            setLecturer();
            setExmID();
            setCMBox();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setCMBox() throws SQLException, ClassNotFoundException {
        ObservableList<String> batches = FXCollections.observableArrayList();
        ArrayList<BatchDTO> allbatches = batchBO.getBatches("");
        for (BatchDTO b : allbatches
        ) {
            batches.add(
                    b.getId()
            );
        }

        cmbBatchID.setItems(batches);
    }

    private void setExmID() throws SQLException, ClassNotFoundException {
        try {
            String lastID = examsBO.getLastID();

            if (lastID.equals("")) {
                txtExamID.setText("SE-1");
            } else {
                String[] array = lastID.split("-");
                int tempNumber = Integer.parseInt(array[1]);
                int finalizeOrderId = tempNumber + 1;
                txtExamID.setText("SE-" + finalizeOrderId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setLecturer() throws SQLException, ClassNotFoundException {
        txtLecID.setText(employeeBO.getIDByToken(Env.token, "lecturer"));

    }

    public void offlineExam(ActionEvent actionEvent) {
        try {
            boolean b = saveExam();

            if (b) {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                new Alert(Alert.AlertType.INFORMATION, "Successfully saved").show();
                stage.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onlineExam(ActionEvent actionEvent) {

        boolean b = false;
        try {
            b = saveExam();
            if (b) {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(".././../view/lecturers/LectureresExamQuestions.fxml"));
                stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.show();

                LecturersExamsQuestionsController rowController = fxmlLoader.getController();
                rowController.setExamID(txtExamID.getText());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean saveExam() throws Exception {
        return examsBO.saveExams(new ExamsDTO(
                txtExamID.getText(),
                pickerDate.getValue(),
                cmbBatchID.getValue(),
                txtLecID.getText()
        ));
    }
}
