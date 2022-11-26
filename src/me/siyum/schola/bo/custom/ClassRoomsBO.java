package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.ClassRoomsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClassRoomsBO {
    ArrayList<ClassRoomsDTO> getAllClassRooms(String s) throws SQLException, ClassNotFoundException;
}