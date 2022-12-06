package me.siyum.schola.controller.receptionist;

import com.jfoenix.controls.JFXCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.AttendanceBO;
import me.siyum.schola.bo.custom.AttendanceMarkBO;
import me.siyum.schola.bo.custom.ClassesBO;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.db.DBConnection;
import me.siyum.schola.dto.AttendanceDTO;
import me.siyum.schola.dto.AttendanceMarkDTO;
import me.siyum.schola.dto.ClassesDTO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.view.receptionist.tm.ReceptionistAttendanceMarkTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReceptionistAttendanceMarkController {
    private final AttendanceBO attendanceBO = BOFactory.getInstance().getBO(BOTypes.ATTENDANCE);
    private final ClassesBO classesBO = BOFactory.getInstance().getBO(BOTypes.CLASSES);
    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private final ObservableList<ReceptionistAttendanceMarkTM> st = FXCollections.observableArrayList();
    private final AttendanceMarkBO attendanceMarkBO = BOFactory.getInstance().getBO(BOTypes.ATTENDANCE_MARK);
    public TableView<ReceptionistAttendanceMarkTM> tblAttendance;
    public TableColumn<ReceptionistAttendanceMarkTM, String> colID;
    public TableColumn<ReceptionistAttendanceMarkTM, String> colName;
    public TableColumn<ReceptionistAttendanceMarkTM, String> colBatch;
    public TableColumn<ReceptionistAttendanceMarkTM, String> colPhone;
    public TableColumn<ReceptionistAttendanceMarkTM, String> colClassRoom;
    public TableColumn<ReceptionistAttendanceMarkTM, String> colActions;
    public JFXCheckBox chkSelectAll;
    int totalSt;
    private String attendanceID = "";
    private String classID = "";

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBatch.setCellValueFactory(new PropertyValueFactory<>("batch"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colClassRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("actions"));

        chkSelectAll.selectedProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<ReceptionistAttendanceMarkTM> items = tblAttendance.getItems();

            for (ReceptionistAttendanceMarkTM ratm : items
            ) {
                ratm.getActions().setSelected(chkSelectAll.isSelected());
            }
        });

    }

    public void setData(String ram) {
        try {
            AttendanceDTO a = attendanceBO.getAttendanceByID(ram);
            ClassesDTO classByID = classesBO.getClassByID(a.getClassID());
            String batch = classByID.getBatch();
            ArrayList<StudentDTO> studentDTOS = studentBO.filterStudents(batch);

            totalSt = studentDTOS.size();
            attendanceID = ram;
            classID = classByID.getId();

            for (StudentDTO s : studentDTOS
            ) {
                CheckBox chk = new CheckBox("");
                st.add(
                        new ReceptionistAttendanceMarkTM(
                                classByID.getId(),
                                s.getName(),
                                s.getId(),
                                s.getBatch(),
                                s.getPhone(),
                                classByID.getClssRoom(),
                                chk
                        )
                );
            }
            tblAttendance.setItems(st);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void abortMarking() {
    }

    public void completeMarking() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        boolean status = true;
        for (ReceptionistAttendanceMarkTM re : st
        ) {

            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            status = attendanceMarkBO.saveAttendanceMarking(
                    new AttendanceMarkDTO(
                            re.getId(),
                            re.getStID(),
                            re.getActions().isSelected()
                    )
            );
        }
        if (status) {
            boolean updateAttendance = attendanceBO.updateAttendance(
                    new AttendanceDTO(
                            attendanceID,
                            classID,
                            LocalDate.now(),
                            totalSt,
                            false
                    )
            );
            if (updateAttendance) {
                new Alert(Alert.AlertType.INFORMATION, "Success").show();
            }
        }

    }
}
