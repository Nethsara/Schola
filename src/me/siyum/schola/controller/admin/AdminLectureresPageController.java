package me.siyum.schola.controller.admin;

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
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.controller.students.StudentFormController;
import me.siyum.schola.dto.EmployeeDTO;
import me.siyum.schola.view.admin.tm.AdminEmployeesTM;
import me.siyum.schola.view.admin.tm.AdminStudentsTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminLectureresPageController {
    public JFXListView<HBox> listLec;

    private final EmployeeBO stBo = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private ObservableList<AdminEmployeesTM> tmList = FXCollections.observableArrayList();

    public void initialize() {
        loadStudents();
        for (AdminEmployeesTM tm : tmList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/me/siyum/schola/view/admin/AdminEmployeeTableRow.fxml"));
            HBox anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
                AdminEmployeeRowController itemController = fxmlLoader.getController();
                itemController.setData(tm);
                listLec.getItems().add(anchorPane);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadStudents() {
        try {
            ArrayList<EmployeeDTO> empDTO = stBo.getEmployee("");
            for (EmployeeDTO s : empDTO) {
                Button btn = new Button("Edit");
                tmList.add(new AdminEmployeesTM(
                        s.getId(),
                        s.getImage(),
                        s.getName(),
                        s.getAddress(),
                        s.getEmail(),
                        s.getSalary(),
                        s.getPaymentMethod(),
                        s.getRole(),
                        s.isStatus()
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

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
