package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.ParentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ParentBO {
    String getLastID() throws SQLException, ClassNotFoundException;

    boolean saveParent(ParentDTO parentDTO) throws SQLException, ClassNotFoundException;

    ParentDTO getParentByID(String id) throws SQLException, ClassNotFoundException;

    ArrayList<ParentDTO> getParents() throws SQLException, ClassNotFoundException;

}
