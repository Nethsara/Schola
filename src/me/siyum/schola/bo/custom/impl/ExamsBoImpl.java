package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.ExamsBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.ExamsDAO;
import me.siyum.schola.dto.ExamsDTO;
import me.siyum.schola.entity.Exams;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExamsBoImpl implements ExamsBO {
    ExamsDAO examsDAO = (ExamsDAO) DAOFactory.getInstance().getDAO(DAOTypes.EXAMS);

    @Override
    public boolean saveExams(ExamsDTO examsDTO) throws Exception {
        return examsDAO.save(
                new Exams(
                        examsDTO.getId(),
                        examsDTO.getDate(),
                        examsDTO.getBatch(),
                        examsDTO.getLecturer()
                )
        );
    }

    @Override
    public ArrayList<ExamsDTO> getAllExams() throws SQLException, ClassNotFoundException {
        ArrayList<Exams> search = examsDAO.search("");
        ArrayList<ExamsDTO> list = new ArrayList<>();
        for (Exams e : search
        ) {
            list.add(
                    new ExamsDTO(
                            e.getId(),
                            e.getDate(),
                            e.getBatch(),
                            e.getLecturer()
                    )
            );
        }
        return list;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return examsDAO.getLastID();
    }

}
