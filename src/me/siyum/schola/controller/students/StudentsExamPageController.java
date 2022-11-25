package me.siyum.schola.controller.students;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.ExamsBO;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.bo.custom.StudentMarkBO;
import me.siyum.schola.bo.custom.SubjectsBO;
import me.siyum.schola.dto.ExamsDTO;
import me.siyum.schola.util.Env;
import me.siyum.schola.view.students.tm.StudentExamTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentsExamPageController {
    private final ExamsBO examsBO = BOFactory.getInstance().getBO(BOTypes.EXAMS);
    private final SubjectsBO subBO = BOFactory.getInstance().getBO(BOTypes.SUBJECTS);
    private final StudentMarkBO stMarkBO = BOFactory.getInstance().getBO(BOTypes.STUDENT_MARK);
    private final StudentBO stBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    public TableColumn colExmID;
    public TableColumn colBy;
    public TableColumn colSub;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colMarks;
    public TableColumn colStatus;
    public TableColumn colActions;
    public TableView<StudentExamTM> tblExm;
    private String stID;
    private ObservableList<StudentExamTM> obList = FXCollections.observableArrayList();

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

        for (ExamsDTO e : allExams
        ) {

            String name = subBO.getNameByLecturer(e.getLecturer());
            double mark = stMarkBO.getMarkByID(e.getId(), stID);

            String status = e.getDate().isBefore(LocalDate.now()) ? "finished" : "pending";
            Button btn = new Button("Participate");

            obList.add(
                    new StudentExamTM(
                            e.getId(),
                            e.getDate(),
                            e.getLecturer(),
                            name,
                            mark,
                            status,
                            btn
                    )
            );
        }
        tblExm.setItems(obList);


    }
}
