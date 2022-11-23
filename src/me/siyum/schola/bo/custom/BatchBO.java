package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.BatchDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BatchBO {
    ArrayList<BatchDTO> getBatches(String s) throws SQLException, ClassNotFoundException;
}
