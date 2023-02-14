package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.FeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FeeBO extends SuperBO {
    ArrayList<FeeDTO> getFeesByID(String stID) throws SQLException, ClassNotFoundException;

    String getLastID() throws SQLException, ClassNotFoundException;

    ArrayList<FeeDTO> getFeesAll() throws SQLException, ClassNotFoundException;
}
