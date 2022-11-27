package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.HomeWorkStudentsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HomeWorkStudentBO {
    ArrayList<HomeWorkStudentsDTO> getHomeWorkStudentsByID(String id) throws SQLException, ClassNotFoundException;

    boolean update(HomeWorkStudentsDTO homeWorkStudentsDTO) throws SQLException, ClassNotFoundException;
}
