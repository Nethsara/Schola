package me.siyum.schola.dao;

import me.siyum.schola.dao.custom.ParentDAO;
import me.siyum.schola.dao.custom.TasksDAO;
import me.siyum.schola.dao.custom.impl.ParentDAOImpl;
import me.siyum.schola.dao.custom.impl.StudentDAOImpl;
import me.siyum.schola.dao.custom.impl.TasksDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return daoFactory==null?daoFactory=(new DAOFactory()) : daoFactory;
    }

    public <T> T getDAO(DAOTypes types){
        switch (types){
            case STUDENT:
                return (T) new StudentDAOImpl();
            case PARENT:
                return (T) new ParentDAOImpl();
            case TASKS:
                return (T) new TasksDAOImpl();
            default:
                return null;
        }
    }

}
