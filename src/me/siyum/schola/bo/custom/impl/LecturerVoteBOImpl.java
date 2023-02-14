package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.LecturerVoteBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.LecturerVoteDAO;
import me.siyum.schola.dto.LecturerVoteDTO;
import me.siyum.schola.entity.LecturerVote;

import java.sql.SQLException;
import java.util.ArrayList;

public class LecturerVoteBOImpl implements LecturerVoteBO {
    LecturerVoteDAO lecturerVoteDAO = (LecturerVoteDAO) DAOFactory.getInstance().getDAO(DAOTypes.LECTURER_VOTE);

    @Override
    public boolean vote(LecturerVoteDTO lecturerVoteDTO) throws SQLException, ClassNotFoundException {
        return lecturerVoteDAO.save(
                new LecturerVote(
                        lecturerVoteDTO.getLecturer(),
                        lecturerVoteDTO.getVote(),
                        lecturerVoteDTO.getDate()
                )
        );
    }

    @Override
    public ArrayList<LecturerVoteDTO> getVotesByLectID(String id) throws SQLException, ClassNotFoundException {
        ArrayList<LecturerVote> search = lecturerVoteDAO.search(id);
        ArrayList<LecturerVoteDTO> list = new ArrayList<>();
        for (LecturerVote l : search
        ) {
            list.add(
                    new LecturerVoteDTO(
                            l.getLecturer(),
                            l.getVote(),
                            l.getDate()
                    )
            );
        }
        return list;
    }
}
