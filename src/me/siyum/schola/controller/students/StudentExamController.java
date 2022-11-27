package me.siyum.schola.controller.students;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.ExamsQuestionsBO;
import me.siyum.schola.dto.ExamQuestionsDTO;
import me.siyum.schola.util.ExamMarking;

import java.io.IOException;
import java.sql.SQLException;

public class StudentExamController {
    public TextField txtQuestion;
    public JFXRadioButton radioMCQ2;
    public JFXRadioButton radioMCQ3;
    public JFXRadioButton radioMCQ4;
    public JFXRadioButton radioMCQ1;

    ExamsQuestionsBO examsQuestionsBO = BOFactory.getInstance().getBO(BOTypes.EXAM_QUESTIONS);
    private ExamQuestionsDTO examQuestionsDTO;
    private int correctAns;
    private int selectedAns;

    public void initialize() {
        setData();
    }


    public void nextButton(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (radioMCQ1.isSelected()) selectedAns = 1;
        if (radioMCQ2.isSelected()) selectedAns = 2;
        if (radioMCQ3.isSelected()) selectedAns = 3;
        if (radioMCQ4.isSelected()) selectedAns = 4;

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

            System.out.println(ExamMarking.mark);
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
        System.out.println(ExamMarking.currentQ);
        try {
            examQuestionsDTO = examsQuestionsBO.getQuestion(ExamMarking.examID, ExamMarking.currentQ);
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

}
