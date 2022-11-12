package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.TasksDTO;
import me.siyum.schola.entity.Tasks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TasksBO {
    int getLastID() throws SQLException, ClassNotFoundException;
    boolean addTasks(Tasks t) throws SQLException, ClassNotFoundException;
    ResultSet retrieveTasks() throws SQLException, ClassNotFoundException;
    ArrayList<TasksDTO> searchTasks(String s) throws SQLException, ClassNotFoundException;
    boolean deleteTasks(int id) throws SQLException, ClassNotFoundException;

}
