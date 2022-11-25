package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.SubjectsBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.SubjectsDAO;
import me.siyum.schola.dto.SubjectsDTO;
import me.siyum.schola.entity.Subjects;

import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectsBOImpl implements SubjectsBO {

    SubjectsDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.SUBJECTS);

    @Override
    public ArrayList<SubjectsDTO> getAllSubjects(String s) throws SQLException, ClassNotFoundException {
        ArrayList<Subjects> search = dao.search(s);
        ArrayList<SubjectsDTO> re = new ArrayList<>();

        for (Subjects sb: search
             ) {
            re.add(
                    new SubjectsDTO(
                            sb.getSubID(),
                            sb.getName(),
                            sb.getLecturer()
                    )
            );
        }

        return re;
    }

    @Override
    public String getNameByLecturer(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Subjects> search = dao.search(id);
        return search.get(0).getName();
    }
}
