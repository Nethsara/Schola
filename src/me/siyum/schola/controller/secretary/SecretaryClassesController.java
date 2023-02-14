package me.siyum.schola.controller.secretary;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.*;
import me.siyum.schola.db.DBConnection;
import me.siyum.schola.dto.*;
import me.siyum.schola.view.secretary.tm.SecretartClassesTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SecretaryClassesController {
    private final BatchBO batchBO = (BatchBO) BOFactory.getInstance().getBO(BOTypes.BATCHES);

    private final ClassSchedulerBO classSchedulerBO = (ClassSchedulerBO) BOFactory.getInstance().getBO(BOTypes.CLASS_SCHEDULER);

    private final ClassRoomsBO classRoomsBO = (ClassRoomsBO) BOFactory.getInstance().getBO(BOTypes.CLASS_ROOMS);
    private final SubjectsBO subjectsBO = (SubjectsBO) BOFactory.getInstance().getBO(BOTypes.SUBJECTS);
    private final ClassesBO classesBO = (ClassesBO) BOFactory.getInstance().getBO(BOTypes.CLASSES);
    private final AttendanceBO attendanceBO = (AttendanceBO) BOFactory.getInstance().getBO(BOTypes.ATTENDANCE);
    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOTypes.STUDENT);
    public JFXComboBox<String> cmbSubID;
    public JFXTextField txtClID;
    public JFXComboBox<String> cmbRoom;
    public JFXDatePicker pickerDate;
    public JFXTimePicker pickerTime;
    public JFXComboBox<String> cmbBatch;
    public TableView<SecretartClassesTM> tblTimeTable;
    public TableColumn<SecretartClassesTM, String> colID;
    public TableColumn<SecretartClassesTM, String> colSubject;
    public TableColumn<SecretartClassesTM, String> colClassRoom;
    public TableColumn<SecretartClassesTM, String> colDate;
    public TableColumn<SecretartClassesTM, String> colTime;
    public TableColumn<SecretartClassesTM, String> colBatch;
    public TableColumn<SecretartClassesTM, String> colActions;
    public JFXButton btnValidation;

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colBatch.setCellValueFactory(new PropertyValueFactory<>("batch"));
        colClassRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        pickerDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.isBefore(today));
            }
        });

        setData();
    }

    private void setData() {
        try {
            setBatchID();
            setClassID();
            setClassRooms();
            setSubjects();
            setTable();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setClassID() throws SQLException, ClassNotFoundException {
        String tempID = classesBO.getLastID();
        if (tempID.equalsIgnoreCase("")) {
            txtClID.setText("SCS-" + 1);
        } else {
            String[] array = tempID.split("-");
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            txtClID.setText("SCS-" + finalizeOrderId);
        }
    }

    private String getAttendanceID() throws SQLException, ClassNotFoundException {
        String tempID = attendanceBO.getLastID();
        if (tempID.equalsIgnoreCase("")) {
            return "SAS-" + 1;
        } else {
            String[] array = tempID.split("-");
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            return "SAS-" + finalizeOrderId;
        }
    }

    private void setBatchID() throws SQLException, ClassNotFoundException {
        ArrayList<BatchDTO> batches = batchBO.getBatches("");
        ObservableList<String> obBatchList = FXCollections.observableArrayList();

        for (BatchDTO b : batches
        ) {
            obBatchList.add(b.getId());
        }

        cmbBatch.setItems(obBatchList);
    }

    private void setClassRooms() throws SQLException, ClassNotFoundException {
        ArrayList<ClassRoomsDTO> batches = classRoomsBO.getAllClassRooms("");
        ObservableList<String> obBatchList = FXCollections.observableArrayList();

        for (ClassRoomsDTO b : batches
        ) {
            obBatchList.add(b.getId());
        }

        cmbRoom.setItems(obBatchList);
    }

    private void setSubjects() throws SQLException, ClassNotFoundException {
        ArrayList<SubjectsDTO> batches = subjectsBO.getAllSubjects("");
        ObservableList<String> obBatchList = FXCollections.observableArrayList();

        for (SubjectsDTO b : batches
        ) {
            obBatchList.add(b.getSubID());
        }

        cmbSubID.setItems(obBatchList);
    }

    private void setTable() throws SQLException, ClassNotFoundException {
        ArrayList<ClassesDTO> allClasses = classesBO.getAllClasses("");
        ObservableList<SecretartClassesTM> list = FXCollections.observableArrayList();
        for (ClassesDTO c : allClasses
        ) {
            JFXButton btn = new JFXButton("Cancel");
            btn.setStyle("-fx-background-color: #c0392b; -fx-text-fill:#ecf0f1;");
            list.add(
                    new SecretartClassesTM(
                            c.getId(),
                            c.getSubID(),
                            c.getClssRoom(),
                            c.getDate(),
                            c.getTime(),
                            c.getBatch(),
                            btn
                    )
            );

        }
        tblTimeTable.setItems(list);
    }

    private ClassesDTO createClassObject() throws SQLException, ClassNotFoundException {
        return new ClassesDTO(
                txtClID.getText(),
                cmbSubID.getValue(),
                subjectsBO.getLecturerBySubID(cmbSubID.getValue()),
                cmbRoom.getValue(),
                cmbBatch.getValue(),
                pickerDate.getValue(),
                pickerTime.getValue()
        );
    }

    private AttendanceDTO getAttendanceDTO() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> studentDTOS = studentBO.filterStudents(cmbBatch.getValue());

        return new AttendanceDTO(
                getAttendanceID(),
                txtClID.getText(),
                pickerDate.getValue(),
                studentDTOS.size(),
                true
        );
    }

    public void scheduleClass() throws SQLException, ClassNotFoundException {

        boolean b = classSchedulerBO.scheduleClass(createClassObject(), getAttendanceDTO());

        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Done").show();
            setTable();
        }
    }
}
