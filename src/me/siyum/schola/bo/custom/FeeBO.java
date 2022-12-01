package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.FeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FeeBO {
    ArrayList<FeeDTO> getFeesByID(String stID) throws SQLException, ClassNotFoundException;

    String getLastID() throws SQLException, ClassNotFoundException;
}
