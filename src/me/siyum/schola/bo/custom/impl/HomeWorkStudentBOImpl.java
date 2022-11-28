package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.HomeWorkStudentBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.HomeWorkStudentDAO;
import me.siyum.schola.dto.HomeWorkStudentsDTO;
import me.siyum.schola.entity.HomeWorkStudents;

import java.sql.SQLException;
import java.util.ArrayList;

public class HomeWorkStudentBOImpl implements HomeWorkStudentBO {
    HomeWorkStudentDAO homeWorkStudentDAO = DAOFactory.getInstance().getDAO(DAOTypes.HOME_WORK_STUDENT);

    @Override
    public ArrayList<HomeWorkStudentsDTO> getHomeWorkStudentsByID(String id) throws SQLException, ClassNotFoundException {
        ArrayList<HomeWorkStudentsDTO> list = new ArrayList<>();
        ArrayList<HomeWorkStudents> search = homeWorkStudentDAO.search(id);
        for (HomeWorkStudents h : search) {
            list.add(
                    new HomeWorkStudentsDTO(
                            h.getId(),
                            h.getStID(),
                            h.getName(),
                            h.getDateSubmitted(),
                            h.isStatus(),
                            h.getFile()
                    )
            );
        }
        return list;
    }

    @Override
    public HomeWorkStudentsDTO getHomeWork(String id, String exmID) throws SQLException, ClassNotFoundException {
        HomeWorkStudents retrieve = homeWorkStudentDAO.retrieve(id, exmID);
        return new HomeWorkStudentsDTO(
                retrieve.getId(),
                retrieve.getStID(),
                retrieve.getName(),
                retrieve.getDateSubmitted(),
                retrieve.isStatus(),
                retrieve.getFile()
        );
    }

    @Override
    public boolean update(HomeWorkStudentsDTO homeWorkStudentsDTO) throws SQLException, ClassNotFoundException {
        return homeWorkStudentDAO.update(
                new HomeWorkStudents(
                        homeWorkStudentsDTO.getId(),
                        homeWorkStudentsDTO.getStID(),
                        homeWorkStudentsDTO.getName(),
                        homeWorkStudentsDTO.getDateSubmitted(),
                        homeWorkStudentsDTO.isStatus(),
                        homeWorkStudentsDTO.getFile()
                )
        );
    }

    @Override
    public boolean saveHomeWorkStudents(HomeWorkStudentsDTO homeWorkStudentsDTO) throws SQLException, ClassNotFoundException {
        return homeWorkStudentDAO.save(
                new HomeWorkStudents(
                        homeWorkStudentsDTO.getId(),
                        homeWorkStudentsDTO.getStID(),
                        homeWorkStudentsDTO.getName(),
                        homeWorkStudentsDTO.getDateSubmitted(),
                        homeWorkStudentsDTO.isStatus(),
                        homeWorkStudentsDTO.getFile()
                )
        );
    }
}
