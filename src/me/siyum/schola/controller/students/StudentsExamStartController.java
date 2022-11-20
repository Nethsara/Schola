package me.siyum.schola.controller.students;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import me.siyum.schola.util.ExamMarking;

public class StudentsExamStartController {

    public TextField txtExID;

    public void startExam(ActionEvent actionEvent) {
        ExamMarking.mark = 0;
        ExamMarking.currentQ = 1;
    }
}
