package me.siyum.schola.controller.students;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.StudentMarkBO;
import me.siyum.schola.db.DBConnection;
import me.siyum.schola.dto.StudentMarkDTO;
import me.siyum.schola.util.ExamMarking;

import java.sql.Connection;
import java.sql.SQLException;

public class StudentExamResultController {
    public Label lblMarks;
    StudentMarkBO studentMarkBO = BOFactory.getInstance().getBO(BOTypes.STUDENT_MARK);


    public void setMarks(String marks) {
        lblMarks.setText(marks);
    }

    private String generateID() {
        try {
            String lastID = studentMarkBO.getLastID();
            if (lastID.equals("")) {
                return "SESM-1";
            } else {
                String[] array = lastID.split("-");
                int tempNumber = Integer.parseInt(array[1]);
                int finalizeOrderId = tempNumber + 1;
                return "SESM-" + finalizeOrderId;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void closeBtn(ActionEvent actionEvent) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean b = studentMarkBO.saveExmStMarks(
                    new StudentMarkDTO(
                            generateID(),
                            ExamMarking.examID,
                            ExamMarking.stID,
                            ExamMarking.mark
                    )
            );
            if (b) {
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                boolean scholaUpdated = ExamMarking.scholaReload();
                if (scholaUpdated) {
                    connection.commit();
                    stage.close();
                } else {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
            } else {
                connection.rollback();
                connection.setAutoCommit(true);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
