package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.ClassesBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.ClassesDAO;
import me.siyum.schola.dto.ClassesDTO;
import me.siyum.schola.entity.Classes;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClassesBOImpl implements ClassesBO {
    ClassesDAO dao = (ClassesDAO) DAOFactory.getInstance().getDAO(DAOTypes.CLASSES);

    @Override
    public ArrayList<ClassesDTO> getAllClasses(String s) throws SQLException, ClassNotFoundException {
        ArrayList<Classes> search = dao.search(s);
        ArrayList<ClassesDTO> clsList = new ArrayList<>();
        for (Classes c : search
        ) {
            clsList.add(
                    new ClassesDTO(
                            c.getId(),
                            c.getSubID(),
                            c.getLecturer(),
                            c.getClssRoom(),
                            c.getBatch(),
                            c.getDate(),
                            c.getTime()
                    )
            );
        }
        return clsList;

    }

    @Override
    public boolean scheduleClass(ClassesDTO classesDTO) throws SQLException, ClassNotFoundException {

        return dao.save(new Classes(
                classesDTO.getId(),
                classesDTO.getSubID(),
                classesDTO.getLecturer(),
                classesDTO.getClssRoom(),
                classesDTO.getBatch(),
                classesDTO.getDate(),
                classesDTO.getTime()
        ));
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return dao.getLastID();
    }

    @Override
    public ClassesDTO getClassByID(String id) throws SQLException, ClassNotFoundException {
        Classes retrieve = dao.retrieve(id);
        return new ClassesDTO(
                retrieve.getId(),
                retrieve.getSubID(),
                retrieve.getLecturer(),
                retrieve.getClssRoom(),
                retrieve.getBatch(),
                retrieve.getDate(),
                retrieve.getTime());
    }
}
