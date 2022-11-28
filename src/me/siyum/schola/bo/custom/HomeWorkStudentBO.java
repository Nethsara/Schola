package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.HomeWorkStudentsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface HomeWorkStudentBO {
    ArrayList<HomeWorkStudentsDTO> getHomeWorkStudentsByID(String id) throws SQLException, ClassNotFoundException;

    HomeWorkStudentsDTO getHomeWork(String id, String exmID) throws SQLException, ClassNotFoundException;

    boolean update(HomeWorkStudentsDTO homeWorkStudentsDTO) throws SQLException, ClassNotFoundException;

    boolean saveHomeWorkStudents(HomeWorkStudentsDTO homeWorkStudentsDTO) throws SQLException, ClassNotFoundException;
}
