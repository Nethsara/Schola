package me.siyum.schola.controller.students;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.HomeWorkBO;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dto.HomeWorkDTO;
import me.siyum.schola.util.Env;
import me.siyum.schola.view.students.tm.StudentHWTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentsHWPageController {
    private final HomeWorkBO homeWorkBO = (HomeWorkBO) BOFactory.getInstance().getBO(BOTypes.HOME_WORK);
    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private final ObservableList<StudentHWTM> list = FXCollections.observableArrayList();
    public JFXComboBox<String> cmbFilter;
    public TableView<StudentHWTM> tblExm;
    public TableColumn<StudentHWTM, String> colHWID;
    public TableColumn<StudentHWTM, String> colLecturer;
    public TableColumn<StudentHWTM, String> colCreated;
    public TableColumn<StudentHWTM, String> colSubmission;
    public TableColumn<StudentHWTM, String> colActions;

    public void initialize() {
        colHWID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLecturer.setCellValueFactory(new PropertyValueFactory<>("lecturer"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        colSubmission.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setData();
    }

    private void setData() {
        try {
            String studentID = studentBO.getStudentByToken(Env.token);

            ArrayList<HomeWorkDTO> allHomeWorks = homeWorkBO.getAllHomeWorks();
            for (HomeWorkDTO d : allHomeWorks
            ) {
                if (studentBO.retrieveStudent(studentID).getBatch().equalsIgnoreCase(d.getBatch())) {
                    Button btn = new Button("Submit");
                    if (d.getSubmissionDate().isBefore(LocalDate.now())) {
                        btn.setDisable(true);
                        btn.setText("Expired");
                    }
                    list.add(
                            new StudentHWTM(
                                    d.getId(),
                                    d.getLecturerID(),
                                    d.getCreateDate(),
                                    d.getSubmissionDate(),
                                    btn
                            )
                    );
                    btn.setOnAction(e -> {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/students/StudentHWUpload.fxml"));
                            Parent parent = loader.load();
                            StudentHWUploadController controller = loader.getController();
                            controller.setData(d);
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.show();

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                }
            }

            tblExm.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void filterOnActions(ActionEvent actionEvent) {
    }
}
