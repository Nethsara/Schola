package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.UsersDTO;

import java.sql.SQLException;

public interface UsersBO extends SuperBO {
    boolean save(UsersDTO u) throws SQLException, ClassNotFoundException;

    String getLastID() throws SQLException, ClassNotFoundException;
}
