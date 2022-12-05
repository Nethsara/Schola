package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.UsersBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.UsersDAO;
import me.siyum.schola.dto.UsersDTO;
import me.siyum.schola.entity.Users;

import java.sql.SQLException;

public class UsersBOImpl implements UsersBO {
    UsersDAO usersDAO = DAOFactory.getInstance().getDAO(DAOTypes.USERS);

    @Override
    public boolean save(UsersDTO u) throws SQLException, ClassNotFoundException {
        return usersDAO.save(
                new Users(
                        u.geteID(),
                        u.getUserName(),
                        u.getPassword(),
                        u.getRole(),
                        u.geteID()
                )
        );
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return usersDAO.getLastID();
    }
}
