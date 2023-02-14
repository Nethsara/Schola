package me.siyum.schola.controller.students;

import com.jfoenix.controls.JFXRadioButton;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.ExamsQuestionsBO;
import me.siyum.schola.dto.ExamQuestionsDTO;
import me.siyum.schola.util.ExamMarking;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentExamController implements Initializable {
    private static final int STARTTIME = 120;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    public JFXRadioButton radioMCQ2;
    public JFXRadioButton radioMCQ3;
    public JFXRadioButton radioMCQ4;
    public JFXRadioButton radioMCQ1;
    public Label lblTime;
    public Label txtQuestion;
    ExamsQuestionsBO examsQuestionsBO = (ExamsQuestionsBO) BOFactory.getInstance().getBO(BOTypes.EXAM_QUESTIONS);
    private Timeline timeline;
    private int correctAns;
    private int selectedAns;



    public void nextButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (radioMCQ1.isSelected()) {
            radioMCQ3.setSelected(false);
            radioMCQ2.setSelected(false);
            radioMCQ4.setSelected(false);
            selectedAns = 1;
        }
        if (radioMCQ2.isSelected()) {
            radioMCQ1.setSelected(false);
            radioMCQ3.setSelected(false);
            radioMCQ4.setSelected(false);
            selectedAns = 2;
        }
        if (radioMCQ3.isSelected()) {
            radioMCQ1.setSelected(false);
            radioMCQ2.setSelected(false);
            radioMCQ4.setSelected(false);
            selectedAns = 3;
        }
        if (radioMCQ4.isSelected()) {
            radioMCQ1.setSelected(false);
            radioMCQ2.setSelected(false);
            radioMCQ3.setSelected(false);
            selectedAns = 4;
        }

        ExamMarking.markPerQuestion = 100 / examsQuestionsBO.getQuestionCount(ExamMarking.examID);

        if (selectedAns == correctAns) {
            ExamMarking.mark += ExamMarking.markPerQuestion;
        }


        int questionCount = examsQuestionsBO.getQuestionCount(ExamMarking.examID);
        if ((questionCount == ExamMarking.currentQ)) {
            try {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/students/StudentsExamResults.fxml"));
                Parent parent = loader.load();
                stage.setScene(new Scene(parent));

                StudentExamResultController controller = loader.getController();
                controller.setMarks(String.valueOf(ExamMarking.mark));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            ExamMarking.currentQ++;
            try {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/students/StudentsExamQuestions.fxml"));
                Parent parent = loader.load();
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void setData() {
        try {
            ExamQuestionsDTO examQuestionsDTO = examsQuestionsBO.getQuestion(ExamMarking.examID, ExamMarking.currentQ);
            txtQuestion.setText(examQuestionsDTO.getQuestion());
            radioMCQ1.setText(examQuestionsDTO.getMcq1());
            radioMCQ2.setText(examQuestionsDTO.getMcq2());
            radioMCQ3.setText(examQuestionsDTO.getMcq3());
            radioMCQ4.setText(examQuestionsDTO.getMcq4());

            correctAns = examQuestionsDTO.getCorrectAns();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }
}
