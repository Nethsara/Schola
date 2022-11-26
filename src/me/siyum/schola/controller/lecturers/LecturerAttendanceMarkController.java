package me.siyum.schola.controller.lecturers;

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
import me.siyum.schola.view.lecturers.tm.LecturerAttendanceMarkTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LecturerAttendanceMarkController {
    private final AttendanceBO attendanceBO = BOFactory.getInstance().getBO(BOTypes.ATTENDANCE);
    private final ClassesBO classesBO = BOFactory.getInstance().getBO(BOTypes.CLASSES);
    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private final ObservableList<LecturerAttendanceMarkTM> st = FXCollections.observableArrayList();
    private final AttendanceMarkBO attendanceMarkBO = BOFactory.getInstance().getBO(BOTypes.ATTENDANCE_MARK);
    public TableView<LecturerAttendanceMarkTM> tblAttendance;
    public TableColumn<LecturerAttendanceMarkTM, String> colID;
    public TableColumn<LecturerAttendanceMarkTM, String> colName;
    public TableColumn<LecturerAttendanceMarkTM, String> colBatch;
    public TableColumn<LecturerAttendanceMarkTM, String> colPhone;
    public TableColumn<LecturerAttendanceMarkTM, String> colClassRoom;
    public TableColumn<LecturerAttendanceMarkTM, String> colActions;
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
            System.out.println("Selected All");
            ObservableList<LecturerAttendanceMarkTM> items = tblAttendance.getItems();

            for (LecturerAttendanceMarkTM ratm : items
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
                        new LecturerAttendanceMarkTM(
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

    public void completeMarking() throws SQLException {
        Connection connection = null;
        boolean status;
        for (LecturerAttendanceMarkTM re : st
        ) {
            try {
                connection = DBConnection.getInstance().getConnection();
                connection.setAutoCommit(false);
                status = attendanceMarkBO.saveAttendanceMarking(
                        new AttendanceMarkDTO(
                                re.getId(),
                                re.getStID(),
                                re.getActions().isSelected()
                        )
                );
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
                        connection.commit();
                        new Alert(Alert.AlertType.INFORMATION, "Success!").show();
                    } else {
                        connection.rollback();
                        connection.setAutoCommit(true);
                    }
                } else {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                assert connection != null;
                connection.setAutoCommit(true);
            }
        }


    }
}
