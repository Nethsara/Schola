package me.siyum.schola.controller.lecturers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.HomeWorkStudentBO;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dto.HomeWorkStudentsDTO;
import me.siyum.schola.view.lecturers.tm.LecturerHomeWorkSubmissionTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class LecturerHomeWorkSubmission {
    private final HomeWorkStudentBO homeWorkStudentBO = BOFactory.getInstance().getBO(BOTypes.HOME_WORK_STUDENT);
    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private final ObservableList<LecturerHomeWorkSubmissionTM> obList = FXCollections.observableArrayList();
    public TableView<LecturerHomeWorkSubmissionTM> tblClasses;
    public TableColumn<LecturerHomeWorkSubmissionTM, String> colID;
    public TableColumn<LecturerHomeWorkSubmissionTM, String> colDate;
    public Label lblHWID;
    public TableColumn<LecturerHomeWorkSubmissionTM, String> colName;
    public TableColumn<LecturerHomeWorkSubmissionTM, String> colView;
    public TableColumn<LecturerHomeWorkSubmissionTM, String> colStatus;
    public TableColumn<LecturerHomeWorkSubmissionTM, String> colActions;

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("stID"));
        colView.setCellValueFactory(new PropertyValueFactory<>("viewBtn"));
        colName.setCellValueFactory(new PropertyValueFactory<>("stName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("submittedDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("action"));

    }

    public void setLabel(String id) {
        try {
            lblHWID.setText(id);
            setData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setData() throws SQLException, ClassNotFoundException {
        System.out.println("Setting data");
        ArrayList<HomeWorkStudentsDTO> homeWorkStudentsByID = homeWorkStudentBO.getHomeWorkStudentsByID(lblHWID.getText());
        System.out.println(homeWorkStudentsByID);
        for (HomeWorkStudentsDTO h : homeWorkStudentsByID) {
            System.out.println(h.getId());
            Button viewBtn = new Button("View");
            Button actionBtn = new Button("Mark As Complete");

            if (!h.isStatus()) {
                actionBtn.setText("Mark As Incomplete");
            }
            System.out.println(h.getStID());

            obList.add(
                    new LecturerHomeWorkSubmissionTM(
                            h.getStID(),
                            studentBO.retrieveStudent(h.getStID()).getName(),
                            h.getDateSubmitted(),
                            h.isStatus(),
                            viewBtn,
                            actionBtn
                    )
            );
            actionBtn.setOnAction(e -> {
                try {
                    if (actionBtn.getText().equalsIgnoreCase("Mark As Complete")) {
                        actionBtn.setText("Mark As Incomplete");
                        homeWorkStudentBO.update(
                                new HomeWorkStudentsDTO(
                                        h.getId(),
                                        h.getStID(),
                                        h.getName(),
                                        h.getDateSubmitted(),
                                        false,
                                        h.getFile()
                                )
                        );
                        tblClasses.refresh();
                    } else if (actionBtn.getText().equalsIgnoreCase("Mark As Incomplete")) {
                        actionBtn.setText("Mark Complete");
                        homeWorkStudentBO.update(
                                new HomeWorkStudentsDTO(
                                        h.getId(),
                                        h.getStID(),
                                        h.getName(),
                                        h.getDateSubmitted(),
                                        true,
                                        h.getFile()
                                )
                        );
                        tblClasses.refresh();
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            });
        }
        tblClasses.setItems(obList);
    }
}
