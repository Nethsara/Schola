package me.siyum.schola.controller.secretary;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.controller.students.StudentFormController;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.view.secretary.tm.SecretartStudentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SecretaryApprovalController {
    private final StudentBO stBo = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    public JFXListView<HBox> listStudents;
    private ObservableList<SecretartStudentTM> tmList = FXCollections.observableArrayList();

    public void initialize() {
        loadStudents();
        for (SecretartStudentTM tm : tmList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/me/siyum/schola/view/secretary/SecretaryTableRow.fxml"));
            HBox anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
                SecretaryTableRowController itemController = fxmlLoader.getController();
                itemController.setData(tm);
                listStudents.getItems().add(anchorPane);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadStudents() {
        try {
            ArrayList<StudentDTO> studentDTOS = stBo.searchStudents(false);
            for (StudentDTO s : studentDTOS) {
                Button btn = new Button("Edit");
                tmList.add(new SecretartStudentTM(
                        s.getId(),
                        s.getImage(),
                        s.getName(),
                        s.getEmail(),
                        s.getNic(),
                        s.getScholaMark(),
                        s.isStatus(),
                        btn
                ));
                btn.setOnAction(e->{
                    System.out.println("Clicked");
                    try {
                        FXMLLoader loader= new FXMLLoader(getClass().getResource(".././../view/students/StudentForm.fxml"));
                        Parent parent = loader.load();
                        StudentFormController controller = loader.getController();
                        controller.setData(s.getId());
                        Stage stage= new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
