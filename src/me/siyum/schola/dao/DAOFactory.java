package me.siyum.schola.dao;

import me.siyum.schola.dao.custom.impl.StudentDAOImpl;

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
            default:
                return null;
        }
    }

}
