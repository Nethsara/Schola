package me.siyum.schola.controller.secretary;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.BatchBO;
import me.siyum.schola.bo.custom.ClassRoomsBO;
import me.siyum.schola.bo.custom.ClassesBO;
import me.siyum.schola.bo.custom.SubjectsBO;
import me.siyum.schola.dto.BatchDTO;
import me.siyum.schola.dto.ClassRoomsDTO;
import me.siyum.schola.dto.ClassesDTO;
import me.siyum.schola.dto.SubjectsDTO;
import me.siyum.schola.view.secretary.tm.SecretartClassesTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SecretaryClassesController {
    private final BatchBO batchBO = BOFactory.getInstance().getBO(BOTypes.BATCHES);
    private final ClassRoomsBO classRoomsBO = BOFactory.getInstance().getBO(BOTypes.CLASS_ROOMS);
    private final SubjectsBO subjectsBO = BOFactory.getInstance().getBO(BOTypes.SUBJECTS);
    private final ClassesBO classesBO = BOFactory.getInstance().getBO(BOTypes.CLASSES);
    public JFXComboBox<String> cmbSubID;
    public JFXTextField txtClID;
    public JFXComboBox<String> cmbRoom;
    public JFXDatePicker pickerDate;
    public JFXTimePicker pickerTime;
    public JFXComboBox<String> cmbBatch;
    public TableView<SecretartClassesTM> tblTimeTable;
    public TableColumn colID;
    public TableColumn colSubject;
    public TableColumn colClassRoom;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colBatch;
    public TableColumn colActions;

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

                setDisable(empty || date.compareTo(today) < 0);
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
            Button btn = new Button("Cancel");
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

    public void scheduleClass(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        classesBO.scheduleClass(
                new ClassesDTO(
                        txtClID.getText(),
                        cmbSubID.getValue(),
                        subjectsBO.getLecturerBySubID(cmbSubID.getValue()),
                        cmbRoom.getValue(),
                        cmbBatch.getValue(),
                        pickerDate.getValue(),
                        pickerTime.getValue()
                )
        );
        setTable();
    }
}
