package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.TasksDTO;
import me.siyum.schola.entity.Tasks;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TasksBO extends SuperBO {
    String getLastID() throws SQLException, ClassNotFoundException;

    boolean addTasks(Tasks t) throws SQLException, ClassNotFoundException;

    ArrayList<TasksDTO> searchTasks(String s) throws SQLException, ClassNotFoundException;

    boolean deleteTasks(String id) throws SQLException, ClassNotFoundException;

}
