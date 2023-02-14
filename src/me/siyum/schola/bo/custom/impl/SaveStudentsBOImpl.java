package me.siyum.schola.bo.custom.impl;

import javafx.scene.control.Alert;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.ParentBO;
import me.siyum.schola.bo.custom.SaveStudentBO;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.bo.custom.UsersBO;
import me.siyum.schola.db.DBConnection;
import me.siyum.schola.dto.ParentDTO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.dto.UsersDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class SaveStudentsBOImpl implements SaveStudentBO {
    private final ParentBO parentBO = (ParentBO) BOFactory.getInstance().getBO(BOTypes.PARENT);
    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOTypes.STUDENT);

    private final UsersBO usersBO = (UsersBO) BOFactory.getInstance().getBO(BOTypes.USERS);

    @Override
    public boolean saveStudentWithNewParent(ParentDTO pdTO, StudentDTO sdTO, UsersDTO usersDTO) {
        Connection connection;
        try {
            connection = DBConnection.getInstance().getConnection();
            boolean saveParent = parentBO.saveParent(pdTO);
            if (saveParent) {
                boolean save = studentBO.saveStudent(sdTO);
                if (save) {
                    boolean generatedUsername = usersBO.save(usersDTO);
                    if (generatedUsername) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        connection.setAutoCommit(true);
                        new Alert(Alert.AlertType.WARNING, "User generated failed!").show();
                    }
                } else {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }
            } else {
                connection.rollback();
                connection.setAutoCommit(true);
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean saveStudentWithOldParent(ParentDTO pdTO, StudentDTO sdTO, UsersDTO usersDTO) throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (!(sdTO == null)) {
                boolean b = studentBO.saveStudent(sdTO);
                if (b) {
                    boolean generatedUsername = usersBO.save(
                            usersDTO);
                    if (generatedUsername) {
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        connection.setAutoCommit(true);
                        new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    }
                } else {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.setAutoCommit(true);
            assert usersDTO != null;
        }
        return false;
    }
}
