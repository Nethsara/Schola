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
import me.siyum.schola.bo.custom.*;
import me.siyum.schola.dto.ExamsDTO;
import me.siyum.schola.util.Env;
import me.siyum.schola.view.students.tm.StudentExamTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentsExamPageController {
    private final ExamsBO examsBO = BOFactory.getInstance().getBO(BOTypes.EXAMS);
    private final SubjectsBO subBO = BOFactory.getInstance().getBO(BOTypes.SUBJECTS);
    private final StudentMarkBO stMarkBO = BOFactory.getInstance().getBO(BOTypes.STUDENT_MARK);
    private final StudentBO stBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private final EmployeeBO empBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private final ObservableList<StudentExamTM> obList = FXCollections.observableArrayList();
    public TableColumn<StudentExamTM, String> colExmID;
    public TableColumn<StudentExamTM, String> colBy;
    public TableColumn<StudentExamTM, String> colSub;
    public TableColumn<StudentExamTM, String> colDate;
    public TableColumn<StudentExamTM, String> colMarks;
    public TableColumn<StudentExamTM, String> colStatus;
    public TableColumn<StudentExamTM, String> colActions;
    public TableView<StudentExamTM> tblExm;
    private String stID;

    public void initialize() {
        colExmID.setCellValueFactory(new PropertyValueFactory<>("exmID"));
        colBy.setCellValueFactory(new PropertyValueFactory<>("by"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colMarks.setCellValueFactory(new PropertyValueFactory<>("marks"));
        colSub.setCellValueFactory(new PropertyValueFactory<>("subject"));

        setData();
    }

    private void setData() {
        try {
            stID = stBO.getStudentByToken(Env.token);
            setTables();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void setTables() throws SQLException, ClassNotFoundException {
        ArrayList<ExamsDTO> allExams = examsBO.getAllExams();

        for (ExamsDTO e : allExams) {

            String name = subBO.getNameByLecturer(e.getLecturer());
            double mark = stMarkBO.getMarkByID(e.getId(), stID);

            String status = e.getDate().isBefore(LocalDate.now()) ? "finished" :
                    e.getDate().isEqual(LocalDate.now()) ? "complete now" : "pending";

            Button btn = new Button("Participate");
            if (status.equals("finished")) {
                btn.setDisable(true);
            } else if (status.equals("pending")) {
                btn.setDisable(true);
            }
            obList.add(
                    new StudentExamTM(
                            e.getId(),
                            e.getDate(),
                            empBO.getEmployeeByID(e.getLecturer()).getName(),
                            name,
                            mark,
                            status,
                            btn
                    )
            );

            btn.setOnAction(ev -> {
                System.out.println("Clicked");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(".././../view/students/StudentsExamReady.fxml"));
                    Parent parent = loader.load();
                    StudentsExamReadyController controller = loader.getController();
                    controller.setData(e.getId(), stID);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(parent));
                    stage.show();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
        tblExm.setItems(obList);


    }
}
