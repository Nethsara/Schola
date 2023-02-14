package me.siyum.schola.controller.students;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.*;
import me.siyum.schola.dto.AnnouncementsDTO;
import me.siyum.schola.dto.ClassesDTO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.dto.StudentMarkDTO;
import me.siyum.schola.util.Env;
import me.siyum.schola.view.students.tm.DasboardAnnTM;
import me.siyum.schola.view.students.tm.StudentClassesTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentDashboardController {
    private final ClassesBO classBO = (ClassesBO) BOFactory.getInstance().getBO(BOTypes.CLASSES);
    private final EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private final SubjectsBO subjectsBO = (SubjectsBO) BOFactory.getInstance().getBO(BOTypes.SUBJECTS);

    public TableColumn<StudentClassesTM, String> colDate;
    public TableColumn<StudentClassesTM, String> colSubject;
    public TableColumn<StudentClassesTM, String> colClassRoom;
    public TableColumn<StudentClassesTM, String> colLecturer;
    public TableColumn<StudentClassesTM, String> colTime;
    public TableView<StudentClassesTM> tblClasses;
    public LineChart studentGrowth;
    public Label lblName;
    public TableColumn<DasboardAnnTM, String> colFrom;
    public TableColumn<DasboardAnnTM, String> colMessage;
    public TableColumn<DasboardAnnTM, String> colID;
    public TableView<DasboardAnnTM> tblAnnounTable;

    private String student;


    public void initialize() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colLecturer.setCellValueFactory(new PropertyValueFactory<>("lecturer"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colClassRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));

        colMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
        colFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));


        setData();
        setAnnouncements();
    }

    private void setAnnouncements() {
        AnnouncementsBO anBO = (AnnouncementsBO) BOFactory.getInstance().getBO(BOTypes.ANNOUNCEMENTS);
        try {
            ArrayList<AnnouncementsDTO> search = anBO.search("");
            ObservableList<DasboardAnnTM> dasboardAnnTMS = FXCollections.observableArrayList();
            for (AnnouncementsDTO a : search
            ) {
                dasboardAnnTMS.add(
                        new DasboardAnnTM(
                                a.getId(),
                                a.getMessage(),
                                a.getFrom()
                        )
                );
            }
            tblAnnounTable.setItems(dasboardAnnTMS);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setData() {
        try {

            student = studentBO.getStudentByToken(Env.token);
            StudentDTO studentDTO = studentBO.retrieveStudent(student);
            lblName.setText(studentDTO.getName());
            setTable();
            setLineChart();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setLineChart() throws SQLException, ClassNotFoundException {
        XYChart.Series series = new XYChart.Series();

        StudentMarkBO studentMarkBO = (StudentMarkBO) BOFactory.getInstance().getBO(BOTypes.STUDENT_MARK);
        ArrayList<StudentMarkDTO> marksByID = studentMarkBO.getMarksByID(student);

        for (StudentMarkDTO mrk : marksByID
        ) {
            series.getData().add(new XYChart.Data(mrk.getExmID(), mrk.getMark()));

        }
        studentGrowth.getData().add(series);
    }

    private void setTable() throws SQLException, ClassNotFoundException {
        final ObservableList<StudentClassesTM> clsTM = FXCollections.observableArrayList();

        StudentDTO studentDTO = studentBO.retrieveStudent(student);

        ArrayList<ClassesDTO> allClasses = classBO.getAllClasses("");
        for (ClassesDTO c : allClasses
        ) {
            if (studentDTO.getBatch().equalsIgnoreCase(c.getBatch())) {
                if (c.getDate().isAfter(LocalDate.now())) {
                    clsTM.add(new StudentClassesTM(
                                    c.getId(),
                                    c.getDate(),
                                    c.getTime(),
                                    subjectsBO.getNameByLecturer(c.getLecturer()),
                                    employeeBO.getEmployeeByID(c.getLecturer()).getName(),
                                    c.getClssRoom(),
                                    c.getDate().isBefore(LocalDate.now()) ? "Passed" : "Upcoming",
                                    new Button("")
                            )
                    );
                }
            }
        }
        tblClasses.setItems(clsTM);
    }

}
