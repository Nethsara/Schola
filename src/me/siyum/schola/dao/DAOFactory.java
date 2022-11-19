package me.siyum.schola.dao;

import me.siyum.schola.dao.custom.impl.*;

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
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case ANNOUNCEMENT:
                return (T) new AnnouncementDAOImpl();
            default:
                return null;
        }
    }

}
