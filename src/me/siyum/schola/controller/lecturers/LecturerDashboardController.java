package me.siyum.schola.controller.lecturers;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.*;
import me.siyum.schola.dto.*;
import me.siyum.schola.util.Env;
import me.siyum.schola.view.lecturers.tm.LecturerDashboardStudentsTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LecturerDashboardController {
    public Label lblTotalSt;
    public Label lblTClasses;
    public Label lblTotalUpcomminLessons;
    public Label lblScholaRating;
    public TableColumn<LecturerDashboardStudentsTM, String> colWID;
    public TableColumn<LecturerDashboardStudentsTM, String> colWName;
    public TableColumn<LecturerDashboardStudentsTM, String> colWMark;
    public TableColumn<LecturerDashboardStudentsTM, JFXButton> colWActions;
    public TableColumn<LecturerDashboardStudentsTM, String> colTID;
    public TableColumn<LecturerDashboardStudentsTM, String> colTName;
    public TableColumn<LecturerDashboardStudentsTM, String> colTMark;
    public TableColumn<LecturerDashboardStudentsTM, JFXButton> colTAction;
    public TableView<LecturerDashboardStudentsTM> tblTop;
    public TableView<LecturerDashboardStudentsTM> tblWeak;
    public Label lblName;
    public LineChart charLecPerform;


    EmployeeDTO lecturer;
    ClassesBO classesBO = BOFactory.getInstance().getBO(BOTypes.CLASSES);
    ClassRoomsBO classRoomsBO = BOFactory.getInstance().getBO(BOTypes.CLASS_ROOMS);
    StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    LecturerVoteBO lecturerVoteBO = BOFactory.getInstance().getBO(BOTypes.LECTURER_VOTE);

    LecturerScholaBO lecturerScholaBO = BOFactory.getInstance().getBO(BOTypes.LECTURER_SCHOLA);

    public void initialize() throws SQLException, ClassNotFoundException {
        lecturer = (EmployeeDTO) Env.user;

        colWID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTID.setCellValueFactory(new PropertyValueFactory<>("id"));

        colWName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTName.setCellValueFactory(new PropertyValueFactory<>("name"));

        colWMark.setCellValueFactory(new PropertyValueFactory<>("scholaMark"));
        colTMark.setCellValueFactory(new PropertyValueFactory<>("scholaMark"));

        colWActions.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colTAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setData();
        setChart();
    }

    private void setChart() throws SQLException, ClassNotFoundException {
        XYChart.Series series = new XYChart.Series();


        ArrayList<LecturerVoteDTO> votesByLectID = lecturerVoteBO.getVotesByLectID(lecturer.getId());

        for (LecturerVoteDTO mrk : votesByLectID
        ) {
            series.getData().add(new XYChart.Data(mrk.getDate(), 5));

        }
        //charLecPerform.getData().add(series);
    }

    private void setData() {
        try {
            lblScholaRating.setText(String.valueOf(lecturerScholaBO.getScholaByID(lecturer.getId()).getMark()));
            setClasses();
            setTables();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTables() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> schola_desc = studentBO.filterStudents("schola desc", "");
        ObservableList<LecturerDashboardStudentsTM> weakOblist = FXCollections.observableArrayList();
        int count = 0;
        for (StudentDTO s : schola_desc
        ) {
            JFXButton btn = new JFXButton("Contact Parent");
            btn.setStyle("-fx-background-color: #02ab56; -fx-text-fill:#ecf0f1;");
            weakOblist.add(
                    new LecturerDashboardStudentsTM(
                            s.getId(),
                            s.getName(),
                            s.getScholaMark(),
                            btn
                    )
            );
            count++;
            if (count > 5) break;
        }

        ArrayList<StudentDTO> schola_asc = studentBO.filterStudents("schola asc", "");
        ObservableList<LecturerDashboardStudentsTM> topOblist = FXCollections.observableArrayList();
        for (StudentDTO s : schola_asc
        ) {
            JFXButton btn = new JFXButton("Contact Parent");
            btn.setStyle("-fx-background-color: #02ab56; -fx-text-fill:#ecf0f1;");
            topOblist.add(
                    new LecturerDashboardStudentsTM(
                            s.getId(),
                            s.getName(),
                            s.getScholaMark(),
                            btn
                    )
            );
            count++;
            if (count > 11) break;
        }

        tblTop.setItems(weakOblist);
        tblWeak.setItems(topOblist);

    }

    private void setClasses() throws SQLException, ClassNotFoundException {
        int classesUp = 0;
        ArrayList<ClassesDTO> allClasses = classesBO.getAllClasses("");
        for (ClassesDTO c : allClasses
        ) {
            if (lecturer.getId().equalsIgnoreCase(c.getLecturer())) {
                if (c.getDate().isAfter(LocalDate.now())) {
                    classesUp++;
                }
            }
        }
        lblTotalUpcomminLessons.setText(String.valueOf(classesUp));

        ArrayList<ClassRoomsDTO> allClassRooms = classRoomsBO.getAllClassRooms("");
        lblTClasses.setText(String.valueOf(allClassRooms.size()));

        ArrayList<StudentDTO> studentDTOS = studentBO.searchStudents("");
        lblTotalSt.setText(String.valueOf(studentDTOS.size()));

        lblName.setText(lecturer.getName());
    }
}
