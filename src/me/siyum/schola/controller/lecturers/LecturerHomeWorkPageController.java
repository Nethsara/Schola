package me.siyum.schola.controller.lecturers;

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
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.bo.custom.HomeWorkBO;
import me.siyum.schola.dto.HomeWorkDTO;
import me.siyum.schola.util.Env;
import me.siyum.schola.view.lecturers.tm.LecturerHomeWorkTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LecturerHomeWorkPageController {
    private final EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private final HomeWorkBO homeWorkBO = BOFactory.getInstance().getBO(BOTypes.HOME_WORK);
    private final ObservableList<LecturerHomeWorkTM> observableList = FXCollections.observableArrayList();
    public TableView<LecturerHomeWorkTM> tblClasses;
    public TableColumn<LecturerHomeWorkTM, String> colID;
    public TableColumn<LecturerHomeWorkTM, String> colSubmitDate;
    public TableColumn<LecturerHomeWorkTM, String> colCreateDate;
    public TableColumn<LecturerHomeWorkTM, String> colBatch;
    public TableColumn<LecturerHomeWorkTM, String> colStatus;

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCreateDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        colSubmitDate.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
        colBatch.setCellValueFactory(new PropertyValueFactory<>("batch"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("btn"));
        setData();
    }

    private void setData() {
        try {
            String lecturerID = employeeBO.getIDByToken(Env.token, "lecturer");
            ArrayList<HomeWorkDTO> homeWorksLecturerID = homeWorkBO.getHomeWorksLecturerID(lecturerID);

            for (HomeWorkDTO hwd : homeWorksLecturerID
            ) {
                Button btn = new Button("View");
                observableList.add(
                        new LecturerHomeWorkTM(
                                hwd.getId(),
                                hwd.getCreateDate(),
                                hwd.getSubmissionDate(),
                                hwd.getBatch(),
                                btn
                        )
                );
                btn.setOnAction(e -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/lecturers/LectureresHomeWorkSubmissions.fxml"));
                        Parent parent = loader.load();
                        LecturerHomeWorkSubmission controller = loader.getController();
                        controller.setLabel(hwd.getId());
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }

            tblClasses.setItems(observableList);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        

    }

    public void newHomeWork(ActionEvent actionEvent) {
    }
}
