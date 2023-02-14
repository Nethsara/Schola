package me.siyum.schola.controller.lecturers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.ClassesBO;
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.dto.ClassesDTO;
import me.siyum.schola.util.Env;
import me.siyum.schola.view.lecturers.tm.LecturerClassesTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LecturerClassesController {
    private final ClassesBO classBO = (ClassesBO) BOFactory.getInstance().getBO(BOTypes.CLASSES);
    private final EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    public TableColumn<LecturerClassesTM, String> colID;
    public TableColumn<LecturerClassesTM, String> colDate;
    public TableColumn<LecturerClassesTM, String> colTime;
    public TableColumn<LecturerClassesTM, String> colSubject;
    public TableColumn<LecturerClassesTM, String> colBatch;
    public TableColumn<LecturerClassesTM, String> colClassroom;
    public TableColumn<LecturerClassesTM, String> colStatus;
    public TableView<LecturerClassesTM> tblClasses;
    private String lecturer;

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colBatch.setCellValueFactory(new PropertyValueFactory<>("batch"));
        colClassroom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        setData();
    }

    private void setData() {
        try {
            lecturer = employeeBO.getIDByToken(Env.token, "lecturer");
            setTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTable() throws SQLException, ClassNotFoundException {
        final ObservableList<LecturerClassesTM> clsTM = FXCollections.observableArrayList();

        ArrayList<ClassesDTO> allClasses = classBO.getAllClasses("");
        for (ClassesDTO c : allClasses
        ) {
            if (lecturer.equalsIgnoreCase(c.getLecturer())) {
                clsTM.add(new LecturerClassesTM(
                                c.getId(),
                                c.getDate(),
                                c.getTime(),
                                c.getLecturer(),
                                c.getBatch(),
                                c.getClssRoom(),
                                c.getDate().isBefore(LocalDate.now()) ? "Passed" : "Upcoming"
                        )
                );
            }
        }
        tblClasses.setItems(clsTM);
    }

}



