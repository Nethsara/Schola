package me.siyum.schola.controller.students;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.ClassesBO;
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.bo.custom.SubjectsBO;
import me.siyum.schola.dto.ClassesDTO;
import me.siyum.schola.dto.LecturerVoteDTO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.util.Env;
import me.siyum.schola.view.students.tm.StudentClassesTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentClassesController {
    private final ClassesBO classBO = (ClassesBO) BOFactory.getInstance().getBO(BOTypes.CLASSES);
    private final EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private final SubjectsBO subjectsBO = (SubjectsBO) BOFactory.getInstance().getBO(BOTypes.SUBJECTS);
    public TableColumn<StudentClassesTM, String> colID;
    public TableColumn<StudentClassesTM, String> colDate;
    public TableColumn<StudentClassesTM, String> colTime;
    public TableColumn<StudentClassesTM, String> colSubject;
    public TableColumn<StudentClassesTM, String> colClassroom;
    public TableColumn<StudentClassesTM, String> colStatus;
    public TableView<StudentClassesTM> tblClasses;
    public TableColumn<StudentClassesTM, String> colLecturer;
    public TableColumn<StudentClassesTM, Button> colActions;
    private String student;

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colLecturer.setCellValueFactory(new PropertyValueFactory<>("lecturer"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colClassroom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setData();
    }

    private void setData() {
        try {
            student = employeeBO.getIDByToken(Env.token, "student");
            setTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTable() throws SQLException, ClassNotFoundException {
        final ObservableList<StudentClassesTM> clsTM = FXCollections.observableArrayList();

        StudentDTO studentDTO = studentBO.retrieveStudent(student);

        ArrayList<ClassesDTO> allClasses = classBO.getAllClasses("");
        for (ClassesDTO c : allClasses
        ) {
            if (studentDTO.getBatch().equalsIgnoreCase(c.getBatch())) {
                Button btn = new Button("Vote");
                btn.setDisable(!c.getDate().isBefore(LocalDate.now()));
                clsTM.add(new StudentClassesTM(
                                c.getId(),
                                c.getDate(),
                                c.getTime(),
                                subjectsBO.getNameByLecturer(c.getLecturer()),
                                employeeBO.getEmployeeByID(c.getLecturer()).getName(),
                                c.getClssRoom(),
                                c.getDate().isBefore(LocalDate.now()) ? "Passed" : "Upcoming",
                                btn
                        )
                );

                btn.setOnAction(event -> {
                    try {
                        btn.setDisable(true);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/students/StudentVote.fxml"));
                        Parent parent = loader.load();
                        StudentVoteController controller = loader.getController();
                        controller.setData(new LecturerVoteDTO(
                                c.getLecturer(), 0, LocalDate.now()
                        ));
                        Stage stage = new Stage();
                        stage.setScene(new Scene(parent));
                        stage.show();

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
        }
        tblClasses.setItems(clsTM);
    }
}
