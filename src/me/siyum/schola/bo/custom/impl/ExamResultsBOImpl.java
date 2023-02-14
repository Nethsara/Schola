package me.siyum.schola.bo.custom.impl;

import javafx.scene.Node;
import javafx.stage.Stage;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.ExamResultsBO;
import me.siyum.schola.bo.custom.StudentMarkBO;
import me.siyum.schola.db.DBConnection;
import me.siyum.schola.dto.StudentMarkDTO;
import me.siyum.schola.util.ExamMarking;

import java.sql.Connection;
import java.sql.SQLException;

public class ExamResultsBOImpl implements ExamResultsBO {
    StudentMarkBO studentMarkBO = (StudentMarkBO) BOFactory.getInstance().getBO(BOTypes.STUDENT_MARK);

    @Override
    public boolean saveResults(StudentMarkDTO studentMarkDTO) {
        Connection connection;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean b = studentMarkBO.saveExmStMarks(studentMarkDTO);
            if (b) {
                boolean scholaUpdated = ExamMarking.scholaReload();
                if (scholaUpdated) {
                    connection.commit();
                    return true;
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
        return false;
    }
}
