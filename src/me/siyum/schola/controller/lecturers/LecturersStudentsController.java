package me.siyum.schola.controller.lecturers;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.view.lecturers.tm.LecturersStudentsTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LecturersStudentsController implements Initializable {
    private final StudentBO stBo = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    public JFXListView<HBox> listStudents;
    private final ObservableList<LecturersStudentsTM> tmList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStudents();
        for (LecturersStudentsTM tm : tmList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/me/siyum/schola/view/lecturers/LecturersTableRow.fxml"));
            HBox anchorPane;
            try {
                anchorPane = fxmlLoader.load();
                LecturersTableRowController rowController = fxmlLoader.getController();
                rowController.setData(tm);
                listStudents.getItems().add(anchorPane);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadStudents() {
        try {
            ArrayList<StudentDTO> studentDTOS = stBo.searchStudents("");
            for (StudentDTO s : studentDTOS) {
                Button btn = new Button("  Edit  ");
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
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
