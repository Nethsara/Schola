package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.TasksBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.TasksDAO;
import me.siyum.schola.dto.TasksDTO;
import me.siyum.schola.entity.Tasks;

import java.sql.SQLException;
import java.util.ArrayList;

public class TasksBOImpl implements TasksBO {
    private final TasksDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.TASKS);

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return dao.getLastID();
    }

    @Override
    public boolean addTasks(Tasks t) throws SQLException, ClassNotFoundException {
        return dao.save(t);
    }


    @Override
    public ArrayList<TasksDTO> searchTasks(String s) throws SQLException, ClassNotFoundException {
        ArrayList<Tasks> tasks = dao.search(s);
        ArrayList<TasksDTO> dtos = new ArrayList<>();
        for (Tasks t : tasks) {
            dtos.add(new TasksDTO(t.getId(), t.getTimeStamp(), t.getMessage(), t.getStatus()));
        }
        return dtos;
    }

    @Override
    public boolean deleteTasks(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }
}
