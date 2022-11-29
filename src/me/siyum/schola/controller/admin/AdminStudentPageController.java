package me.siyum.schola.controller.admin;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.ExamsBO;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.controller.students.StudentFormController;
import me.siyum.schola.dto.ExamsDTO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.view.admin.tm.AdminStudentsTM;
import me.siyum.schola.view.lecturers.tm.LecturersStudentsTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminStudentPageController {
    private final StudentBO stBo = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private final ExamsBO examsBO = BOFactory.getInstance().getBO(BOTypes.EXAMS);
    public JFXListView<HBox> listStudents;
    public TextField txtSearch;
    public JFXComboBox<String> cmbSortBox;
    public JFXComboBox<String> cmbExam;

    public void initialize() {
        setData();
    }

    private void setData() {
        try {
            setSortBy();
            setExmCMB();
            loadTable(stBo.searchStudents(""));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setExmCMB() throws SQLException, ClassNotFoundException {
        ArrayList<ExamsDTO> allExams = examsBO.getAllExams();
        ObservableList<String> exmIDs = FXCollections.observableArrayList();
        for (ExamsDTO e : allExams
        ) {
            exmIDs.add(e.getId());
        }
        cmbExam.setItems(exmIDs);
    }

    private void loadTable(ArrayList<StudentDTO> studentDTOS) {
        listStudents.getItems().clear();
        ObservableList<LecturersStudentsTM> tmList = FXCollections.observableArrayList();
        for (StudentDTO s : studentDTOS) {
            Button btn = new Button("Edit");
            tmList.add(new LecturersStudentsTM(
                    s.getId(),
                    s.getImage(),
                    s.getName(),
                    s.getEmail(),
                    s.getNic(),
                    s.getScholaMark(),
                    s.isStatus(),
                    btn
            ));
            btn.setOnAction(e -> {
                System.out.println("Clicked");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(".././../view/students/StudentForm.fxml"));
                    Parent parent = loader.load();
                    StudentFormController controller = loader.getController();
                    controller.setData(s.getId());
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.show();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }

        for (LecturersStudentsTM tm : tmList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/me/siyum/schola/view/admin/AdminStudentTableRow.fxml"));
            HBox anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
                AdminStudentsRowController itemController = fxmlLoader.getController();
                itemController.setData(
                        new AdminStudentsTM(
                                tm.getId(),
                                tm.getImage(),
                                tm.getName(),
                                tm.getEmail(),
                                tm.getNic(),
                                tm.getScholaMarks(),
                                tm.getStatus(),
                                tm.getBtn()
                        ));
                listStudents.getItems().add(anchorPane);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void setSortBy() {
        String[] ar = {"weak", "top", "id asc", "id desc", "name asc", "name desc", "schola asc", "schola desc"};
        ObservableList<String> obList = FXCollections.observableArrayList(ar);
        cmbSortBox.setItems(obList);
    }

    public void sortByOnAction(ActionEvent actionEvent) {
        try {
            ArrayList<StudentDTO> studentDTOS = stBo.filterStudents(cmbSortBox.getValue(), cmbExam.getValue());
            loadTable(studentDTOS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
