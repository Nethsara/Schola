package me.siyum.schola.controller.receptionist;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.AttendanceBO;
import me.siyum.schola.bo.custom.ClassesBO;
import me.siyum.schola.dto.AttendanceDTO;
import me.siyum.schola.dto.ClassesDTO;
import me.siyum.schola.view.receptionist.tm.ReceptionistAttendanceTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReceptionistAttendanceController {
    private final ObservableList<ReceptionistAttendanceTM> pendingClzList = FXCollections.observableArrayList();
    private final ObservableList<ReceptionistAttendanceTM> finishedClzList = FXCollections.observableArrayList();
    private final ClassesBO classesBO = BOFactory.getInstance().getBO(BOTypes.CLASSES);
    private final AttendanceBO attendanceBO = BOFactory.getInstance().getBO(BOTypes.ATTENDANCE);
    public AnchorPane paneAttendance;
    public TableView<ReceptionistAttendanceTM> tblAttendance;
    public TableColumn<ReceptionistAttendanceTM, String> colID;
    public TableColumn<ReceptionistAttendanceTM, String> colBatch;
    public TableColumn<ReceptionistAttendanceTM, String> colDate;
    public TableColumn<ReceptionistAttendanceTM, String> colTime;
    public TableColumn<ReceptionistAttendanceTM, String> colClassRoom;
    public TableColumn<ReceptionistAttendanceTM, String> colLectures;
    public TableColumn<ReceptionistAttendanceTM, String> colActions;
    public JFXComboBox<String> cmbFilter;

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colBatch.setCellValueFactory(new PropertyValueFactory<>("batch"));
        colClassRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colLectures.setCellValueFactory(new PropertyValueFactory<>("lecturer"));

        setData();
    }

    private void setData() {
        try {
            setComboBox();
            loadObLists();
            setPendingClasses();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setComboBox() {
        String[] ar = {"pending", "finished"};
        ObservableList<String> obList = FXCollections.observableArrayList(ar);
        cmbFilter.setItems(obList);
    }

    private void loadObLists() throws SQLException, ClassNotFoundException {
        ArrayList<AttendanceDTO> allAttendance = attendanceBO.getAllAttendance();

        for (AttendanceDTO a : allAttendance
        ) {
            ClassesDTO classesDTO = classesBO.getClassByID(a.getClassID());
            Button btn = new Button("Mark");
            if (a.getDate().isBefore(LocalDate.now()) && !a.isStatus()) {
                btn.setDisable(true);
                finishedClzList.add(
                        new ReceptionistAttendanceTM(
                                a.getClassID(),
                                a.getId(),
                                classesDTO.getBatch(),
                                a.getDate(),
                                classesDTO.getTime(),
                                classesDTO.getClssRoom(),
                                classesDTO.getLecturer(),
                                btn
                        )
                );
            } else {
                if (!a.isStatus()) {
                    btn.setText("Marked");
                    btn.setDisable(true);
                }
                pendingClzList.add(
                        new ReceptionistAttendanceTM(
                                a.getClassID(),
                                a.getId(),
                                classesDTO.getBatch(),
                                a.getDate(),
                                classesDTO.getTime(),
                                classesDTO.getClssRoom(),
                                classesDTO.getLecturer(),
                                btn
                        )
                );
            }
            btn.setOnAction(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/receptionist/ReceptionistAttendanceMark.fxml"));
                    Parent parent = loader.load();
                    ReceptionistAttendanceMarkController controller = loader.getController();
                    controller.setData(a.getId());
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.show();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

        }
    }

    private void setPendingClasses() {
        tblAttendance.setItems(pendingClzList);

    }

    private void setFinishedClasses() {
        tblAttendance.setItems(finishedClzList);

    }

    public void filterOnAction() {
        if (cmbFilter.getValue().equalsIgnoreCase("pending")) {
            setPendingClasses();
        } else {
            setFinishedClasses();
        }
    }
}
