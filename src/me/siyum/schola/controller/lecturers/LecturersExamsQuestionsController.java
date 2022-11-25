package me.siyum.schola.controller.lecturers;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.ExamsQuestionsBO;
import me.siyum.schola.dto.ExamQuestionsDTO;
import me.siyum.schola.view.lecturers.tm.LecturerExamQuestionTM;

import java.util.Optional;

public class LecturersExamsQuestionsController {
    public TextField txtQuestion;
    public TextField txtMCQ1;
    public TextField txtMCQ2;
    public TextField txtMCQ3;
    public TextField txtMCQ4;
    public JFXComboBox cmbCorrectAnswer;
    public TableColumn colQNo;
    public TableColumn colQuestion;
    public TableColumn colMCQ1;
    public TableColumn colMCQ2;
    public TableColumn colMCQ3;
    public TableColumn colMCQ4;
    public TableColumn colAction;
    public TableView<LecturerExamQuestionTM> tblQuestions;
    public TableColumn colCorrectAns;
    public Label lblExmID;

    ObservableList<LecturerExamQuestionTM> obList = FXCollections.observableArrayList();
    ObservableList<String> answers = FXCollections.observableArrayList();

    ExamsQuestionsBO examsQuestionsBO = BOFactory.getInstance().getBO(BOTypes.EXAM_QUESTIONS);

    public void initialize() {
        answers.add("1");
        answers.add("2");
        answers.add("3");
        answers.add("4");

        cmbCorrectAnswer.setItems(answers);

        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colQuestion.setCellValueFactory(new PropertyValueFactory<>("question"));
        colQNo.setCellValueFactory(new PropertyValueFactory<>("questionNo"));
        colMCQ1.setCellValueFactory(new PropertyValueFactory<>("mcq1"));
        colMCQ2.setCellValueFactory(new PropertyValueFactory<>("mcq2"));
        colMCQ3.setCellValueFactory(new PropertyValueFactory<>("mcq3"));
        colMCQ4.setCellValueFactory(new PropertyValueFactory<>("mcq4"));
        colCorrectAns.setCellValueFactory(new PropertyValueFactory<>("correctAns"));

        setData();
    }

    private void setData() {
    }

    public void setExamID(String id) {
        lblExmID.setText(id);
    }

    public void createExm(ActionEvent actionEvent) {
        System.out.println("create");
        boolean status = true;
        try {
            for (LecturerExamQuestionTM e : obList
            ) {

                status = examsQuestionsBO.saveExamQuestion(new ExamQuestionsDTO(
                        e.getQuestionNo(),
                        e.getExmID(),
                        e.getQuestion(),
                        e.getMcq1(),
                        e.getMcq2(),
                        e.getMcq3(),
                        e.getMcq4(),
                        e.getCorrectAns()
                ));


            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (status) {
            new Alert(Alert.AlertType.INFORMATION, "Done").show();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.close();

        }
    }

    public void addQuestion(ActionEvent actionEvent) {
        Button btn = new Button("Remove");
        obList.add(
                new LecturerExamQuestionTM(
                        obList.size() > 0 ? obList.get(obList.size() - 1).getQuestionNo() + 1 : 1,
                        lblExmID.getText(),
                        txtQuestion.getText(),
                        txtMCQ1.getText(),
                        txtMCQ2.getText(),
                        txtMCQ3.getText(),
                        txtMCQ4.getText(),
                        Integer.parseInt(cmbCorrectAnswer.getValue().toString()),
                        btn
                )
        );
        btn.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get() == ButtonType.YES) {
                for (LecturerExamQuestionTM tm : obList
                ) {
                    obList.remove(tm);
                    tblQuestions.refresh();
                    return;
                }
            }

        });
        tblQuestions.setItems(obList);
        tblQuestions.refresh();
    }
}
