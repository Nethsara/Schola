package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.HomeWorkDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HomeWorkBO extends SuperBO {
    ArrayList<HomeWorkDTO> getHomeWorksLecturerID(String id) throws SQLException, ClassNotFoundException;

    ArrayList<HomeWorkDTO> getAllHomeWorks() throws SQLException, ClassNotFoundException;

    boolean save(HomeWorkDTO hw) throws SQLException, ClassNotFoundException;
}
