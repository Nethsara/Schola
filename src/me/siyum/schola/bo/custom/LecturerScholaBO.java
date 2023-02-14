package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.LecturerScholaDTO;

import java.sql.SQLException;

public interface LecturerScholaBO extends SuperBO {
    LecturerScholaDTO getScholaByID(String id) throws SQLException, ClassNotFoundException;
}
