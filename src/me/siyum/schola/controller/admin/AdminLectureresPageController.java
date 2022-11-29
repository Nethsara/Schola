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
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.bo.custom.LecturerScholaBO;
import me.siyum.schola.controller.students.StudentFormController;
import me.siyum.schola.dto.EmployeeDTO;
import me.siyum.schola.view.admin.tm.AdminEmployeesTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminLectureresPageController {
    private final EmployeeBO empBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    public JFXListView<HBox> listLec;
    public JFXComboBox<String> cmbSortBy;
    public TextField txtSearch;
    LecturerScholaBO lecturerScholaBO = BOFactory.getInstance().getBO(BOTypes.LECTURER_SCHOLA);

    public void initialize() {
        setData();
    }

    public void setData() {
        try {
            setSortBy();
            loadTable(empBO.getEmployeeByType(""));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadTable(ArrayList<EmployeeDTO> empDTO) throws SQLException, ClassNotFoundException {
        listLec.getItems().clear();
        ObservableList<AdminEmployeesTM> tmList = FXCollections.observableArrayList();

        for (EmployeeDTO s : empDTO) {
            Button btn = new Button("Edit");
            int lecturer = empBO.getEmployeeByID(s.getId()).getRole().equalsIgnoreCase("lecturer") ? lecturerScholaBO.getScholaByID(s.getId()).getMark() : 0;
            tmList.add(new AdminEmployeesTM(
                    s.getId(),
                    s.getImage(),
                    s.getName(),
                    s.getAddress(),
                    s.getEmail(),
                    s.getSalary(),
                    s.getPaymentMethod(),
                    s.getRole(),
                    s.isStatus(),
                    String.valueOf(lecturer)
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

    public void newEmployee(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(".././../view/admin/EmployeeForm.fxml"));
        try {
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setSortBy() {
        String[] ar = {"receptionist", "lecturers", "minor", "secretary", "id asc", "id desc", "name asc", "name desc"};
        ObservableList<String> obList = FXCollections.observableArrayList(ar);
        cmbSortBy.setItems(obList);
    }

    public void sortOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOS = empBO.filterEmployees(cmbSortBy.getValue());
        loadTable(employeeDTOS);
    }
}
