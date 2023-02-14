package me.siyum.schola.controller.receptionist;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.view.receptionist.tm.ReceptionistStudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReceptionistStudentsController implements Initializable {

    private final StudentBO stBo = (StudentBO) BOFactory.getInstance().getBO(BOTypes.STUDENT);
    public JFXListView<HBox> listStudents;
    private final ObservableList<ReceptionistStudentTM> tmList = FXCollections.observableArrayList();

    private void loadStudents() {
        try {
            ArrayList<StudentDTO> studentDTOS = stBo.searchStudents("");
            for (StudentDTO s : studentDTOS) {
                Button btn = new Button("Edit");
                tmList.add(new ReceptionistStudentTM(
                        s.getId(),
                        s.getImage(),
                        s.getName(),
                        s.getEmail(),
                        s.getNic(),
                        s.getScholaMark(),
                        s.isStatus(),
                        btn
                ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void newStudent(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load((getClass().getResource(".././../view/students/StudentForm.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStudents();
        for (ReceptionistStudentTM tm : tmList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/me/siyum/schola/view/receptionist/StudentTableRow.fxml"));
            HBox anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
                ReceptionistTableRowController rowController = fxmlLoader.getController();
                rowController.setData(tm);
                listStudents.getItems().add(anchorPane);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
