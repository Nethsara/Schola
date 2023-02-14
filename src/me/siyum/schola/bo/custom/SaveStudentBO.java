package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.ParentDTO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.dto.UsersDTO;

import java.sql.SQLException;

public interface SaveStudentBO extends SuperBO {
    boolean saveStudentWithNewParent(ParentDTO pdTO, StudentDTO sdTO, UsersDTO usersDTO) throws SQLException, ClassNotFoundException;

    boolean saveStudentWithOldParent(ParentDTO pdTO, StudentDTO sdTO, UsersDTO usersDTO) throws SQLException;
}
