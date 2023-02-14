package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.BatchDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BatchBO extends SuperBO {
    ArrayList<BatchDTO> getBatches(String s) throws SQLException, ClassNotFoundException;

    BatchDTO getBatch(String id) throws SQLException, ClassNotFoundException;
}
