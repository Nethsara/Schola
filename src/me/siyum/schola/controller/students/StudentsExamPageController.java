package me.siyum.schola.controller.students;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private final ObservableList<StudentExamTM> todayExams = FXCollections.observableArrayList();
    private final ObservableList<StudentExamTM> passedExams = FXCollections.observableArrayList();
    private final ObservableList<StudentExamTM> pendingExams = FXCollections.observableArrayList();
    public TableColumn<StudentExamTM, String> colExmID;
    public TableColumn<StudentExamTM, String> colBy;
    public TableColumn<StudentExamTM, String> colSub;
    public TableColumn<StudentExamTM, String> colDate;
    public TableColumn<StudentExamTM, String> colMarks;
    public TableColumn<StudentExamTM, String> colStatus;
    public TableColumn<StudentExamTM, String> colActions;
    public TableView<StudentExamTM> tblExm;
    public JFXComboBox<String> cmbFilter;
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
            loadLists();
            setComboBox();
            setTables(todayExams);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setComboBox() {
        ObservableList<String> filterList = FXCollections.observableArrayList(
                "Today",
                "Passed",
                "Pending"
        );

        cmbFilter.setItems(filterList);
    }

    private void loadPassedExams() throws SQLException, ClassNotFoundException {
        setTables(passedExams);
    }

    private void loadTodayExams() throws SQLException, ClassNotFoundException {
        setTables(todayExams);
    }

    private void loadPendingExams() throws SQLException, ClassNotFoundException {
        setTables(pendingExams);
    }

    private void loadLists() throws SQLException, ClassNotFoundException {
        ArrayList<ExamsDTO> allExams = examsBO.getAllExams();

        for (ExamsDTO e : allExams) {

            String name = subBO.getNameByLecturer(e.getLecturer());
            double mark = stMarkBO.getMarkByID(stID, e.getId());

            String status = e.getDate().isBefore(LocalDate.now()) ? "finished" :
                    e.getDate().isEqual(LocalDate.now()) ? "today" : "pending";

            System.out.println(mark + " mark from db " + e.getId() + " - Exm " + stID + " st ");
            Button btn = new Button("Participate");

            if (mark > -1) {
                btn.setText("Already Done");
                btn.setDisable(true);
            }
            if (status.equals("finished") || status.equals("pending")) {
                btn.setDisable(true);
            }

            if (status.equalsIgnoreCase("finished")) {
                passedExams.add(
                        new StudentExamTM(
                                e.getId(),
                                e.getDate(),
                                empBO.getEmployeeByID(e.getLecturer()).getName(),
                                name,
                                mark < 0 ? 0 : mark,
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

            if (status.equalsIgnoreCase("pending")) {
                pendingExams.add(
                        new StudentExamTM(
                                e.getId(),
                                e.getDate(),
                                empBO.getEmployeeByID(e.getLecturer()).getName(),
                                name,
                                mark < 0 ? 0 : mark,
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

            if (status.equalsIgnoreCase("today")) {
                todayExams.add(
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
        }
    }

    private void setTables(ObservableList<StudentExamTM> obList) throws SQLException, ClassNotFoundException {
        tblExm.setItems(obList);
    }

    public void filterOnActions(ActionEvent actionEvent) {
        try {
            if (cmbFilter.getValue().equalsIgnoreCase("Today")) {
                loadTodayExams();
            } else if (cmbFilter.getValue().equalsIgnoreCase("Passed")) {
                loadPassedExams();
            } else {
                loadPendingExams();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
