package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.LecturerScholaBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.LecturerScholaDAO;
import me.siyum.schola.dto.LecturerScholaDTO;
import me.siyum.schola.entity.LecturerSchola;

import java.sql.SQLException;

public class LecturerScholaBOImpl implements LecturerScholaBO {
    LecturerScholaDAO lecturerScholaDAO = DAOFactory.getInstance().getDAO(DAOTypes.LECTURER_SCHOLA);

    @Override
    public LecturerScholaDTO getScholaByID(String id) throws SQLException, ClassNotFoundException {
        LecturerSchola retrieve = lecturerScholaDAO.retrieve(id);
        return new LecturerScholaDTO(
                retrieve.getLecID(),
                retrieve.getMark()
        );
    }
}
