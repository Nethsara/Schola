package me.siyum.schola.controller.lecturers;

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
import me.siyum.schola.bo.custom.ExamsBO;
import me.siyum.schola.dto.ExamsDTO;
import me.siyum.schola.view.lecturers.tm.LecturerExamsTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LecturersExamsController {
    public TableView<LecturerExamsTM> tblExm;
    public TableColumn colID;
    public TableColumn colDate;
    public TableColumn colBatch;
    public TableColumn colActions;
    ExamsBO examsBo = (ExamsBO) BOFactory.getInstance().getBO(BOTypes.EXAMS);

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colBatch.setCellValueFactory(new PropertyValueFactory<>("batch"));
        setData();
    }

    private void setData() {
        try {
            setTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTable() throws SQLException, ClassNotFoundException {
        ArrayList<ExamsDTO> allExams = examsBo.getAllExams();
        ObservableList<LecturerExamsTM> list = FXCollections.observableArrayList();

        for (ExamsDTO e : allExams
        ) {
            Button btn = new Button("View");
            list.add(
                    new LecturerExamsTM(
                            e.getId(),
                            e.getDate(),
                            e.getBatch(),
                            btn
                    )
            );
        }
        tblExm.setItems(list);


    }

    public void newExam() throws IOException {
        Parent parent = FXMLLoader.load((getClass().getResource("../../view/lecturers/LectureresExamsScheduler.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
