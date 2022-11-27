package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.HomeWorkBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.HomeWorkDAO;
import me.siyum.schola.dto.HomeWorkDTO;
import me.siyum.schola.entity.HomeWorks;

import java.sql.SQLException;
import java.util.ArrayList;

public class HomeWorkBOImpl implements HomeWorkBO {
    private final HomeWorkDAO homeWorkDAO = DAOFactory.getInstance().getDAO(DAOTypes.HOME_WORK);

    @Override
    public ArrayList<HomeWorkDTO> getHomeWorksLecturerID(String id) throws SQLException, ClassNotFoundException {
        ArrayList<HomeWorks> search = homeWorkDAO.search(id);
        ArrayList<HomeWorkDTO> list = new ArrayList<>();

        for (HomeWorks h : search
        ) {
            list.add(
                    new HomeWorkDTO(
                            h.getId(),
                            h.getLecturerID(),
                            h.getCreateDate(),
                            h.getSubmissionDate(),
                            h.getBatch(),
                            h.getMessage()
                    )
            );
        }
        return list;
    }

    @Override
    public boolean save(HomeWorkDTO hw) throws SQLException, ClassNotFoundException {
        return homeWorkDAO.save(
                new HomeWorks(
                        hw.getId(),
                        hw.getLecturerID(),
                        hw.getCreateDate(),
                        hw.getSubmissionDate(),
                        hw.getBatch(),
                        hw.getMessage()
                )
        );
    }
}
