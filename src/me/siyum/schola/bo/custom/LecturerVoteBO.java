package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.LecturerVoteDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LecturerVoteBO extends SuperBO {
    boolean vote(LecturerVoteDTO lecturerVoteDTO) throws SQLException, ClassNotFoundException;

    ArrayList<LecturerVoteDTO> getVotesByLectID(String id) throws SQLException, ClassNotFoundException;
}
