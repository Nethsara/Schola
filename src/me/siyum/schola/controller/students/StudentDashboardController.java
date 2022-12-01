package me.siyum.schola.controller.students;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.*;
import me.siyum.schola.dto.ClassesDTO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.dto.StudentMarkDTO;
import me.siyum.schola.util.Env;
import me.siyum.schola.view.students.tm.StudentClassesTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentDashboardController {
    private final ClassesBO classBO = BOFactory.getInstance().getBO(BOTypes.CLASSES);
    private final EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private final SubjectsBO subjectsBO = BOFactory.getInstance().getBO(BOTypes.SUBJECTS);

    public TableColumn<StudentClassesTM, String> colDate;
    public TableColumn<StudentClassesTM, String> colSubject;
    public TableColumn<StudentClassesTM, String> colClassRoom;
    public TableColumn<StudentClassesTM, String> colLecturer;
    public TableColumn<StudentClassesTM, String> colTime;
    public TableView<StudentClassesTM> tblClasses;
    public LineChart studentGrowth;

    private String student;


    public void initialize() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colLecturer.setCellValueFactory(new PropertyValueFactory<>("lecturer"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colClassRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));

        setData();
    }

    private void setData() {
        try {
            student = employeeBO.getIDByToken(Env.token, "student");
            setTable();
            setLineChart();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setLineChart() throws SQLException, ClassNotFoundException {
        XYChart.Series series = new XYChart.Series();

        StudentMarkBO studentMarkBO = BOFactory.getInstance().getBO(BOTypes.STUDENT_MARK);
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
            System.out.println(c.getLecturer());
            if (studentDTO.getBatch().equalsIgnoreCase(c.getBatch())) {
                if (c.getDate().isAfter(LocalDate.now())) {
                    clsTM.add(new StudentClassesTM(
                                    c.getId(),
                                    c.getDate(),
                                    c.getTime(),
                                    subjectsBO.getNameByLecturer(c.getLecturer()),
                                    employeeBO.getEmployeeByID(c.getLecturer()).getName(),
                                    c.getClssRoom(),
                                    c.getDate().isBefore(LocalDate.now()) ? "Passed" : "Upcoming"
                            )
                    );
                }
            }
        }
        tblClasses.setItems(clsTM);
    }

}
