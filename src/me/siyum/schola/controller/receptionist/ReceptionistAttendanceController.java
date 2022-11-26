package me.siyum.schola.controller.receptionist;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.ClassesBO;
import me.siyum.schola.dto.ClassesDTO;
import me.siyum.schola.view.receptionist.tm.ReceptionistAttendanceTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReceptionistAttendanceController {
    private final ObservableList<ReceptionistAttendanceTM> pendingClzList = FXCollections.observableArrayList();
    private final ObservableList<ReceptionistAttendanceTM> finishedClzList = FXCollections.observableArrayList();
    private final ClassesBO classesBO = BOFactory.getInstance().getBO(BOTypes.CLASSES);
    public AnchorPane paneAttendance;
    public TableView<ReceptionistAttendanceTM> tblAttendance;
    public TableColumn colID;
    public TableColumn colBatch;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colClassRoom;
    public TableColumn colLectures;
    public TableColumn colActions;
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
        ArrayList<ClassesDTO> allClasses = classesBO.getAllClasses("");

        for (ClassesDTO c : allClasses
        ) {
            Button btn = new Button("Mark");
            if (c.getDate().isBefore(LocalDate.now())) {
                btn.setDisable(true);
                finishedClzList.add(
                        new ReceptionistAttendanceTM(
                                c.getId(),
                                c.getBatch(),
                                c.getDate(),
                                c.getTime(),
                                c.getClssRoom(),
                                c.getLecturer(),
                                btn
                        )
                );
            } else {
                btn.setDisable(false);
                pendingClzList.add(
                        new ReceptionistAttendanceTM(
                                c.getId(),
                                c.getBatch(),
                                c.getDate(),
                                c.getTime(),
                                c.getClssRoom(),
                                c.getLecturer(),
                                btn
                        )
                );
            }
        }

    }

    private void setPendingClasses() {
        tblAttendance.setItems(pendingClzList);

    }

    private void setFinishedClasses() {
        tblAttendance.setItems(finishedClzList);

    }

    public void filterOnAction(ActionEvent actionEvent) {
        if (cmbFilter.getValue().equalsIgnoreCase("pending")) {
            System.out.println("pending");
            setPendingClasses();
        } else {
            System.out.println("finished");
            setFinishedClasses();
        }
    }
}
