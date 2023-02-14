package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.ParentBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.impl.ParentDAOImpl;
import me.siyum.schola.dto.ParentDTO;
import me.siyum.schola.entity.Parent;

import java.sql.SQLException;
import java.util.ArrayList;

public class ParentBOImpl implements ParentBO {
    private final ParentDAOImpl dao = (ParentDAOImpl) DAOFactory.getInstance().getDAO(DAOTypes.PARENT);

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return dao.getLastID();
    }

    @Override
    public boolean saveParent(ParentDTO parentDTO) throws SQLException, ClassNotFoundException {
        return dao.save(new Parent(
                parentDTO.getId(),
                parentDTO.getName(),
                parentDTO.getEmail(),
                parentDTO.getNic(),
                parentDTO.getAddress(),
                parentDTO.getPhone()
        ));
    }

    @Override
    public ParentDTO getParentByID(String id) throws SQLException, ClassNotFoundException {
        Parent parent = dao.retrieve(id);
        return new ParentDTO(
                parent.getId(),
                parent.getName(),
                parent.getEmail(),
                parent.getNic(),
                parent.getAddress(),
                parent.getPhone()
        );

    }

    @Override
    public ArrayList<ParentDTO> getParents() throws SQLException, ClassNotFoundException {
        ArrayList<Parent> search = dao.search("");
        ArrayList<ParentDTO> list = new ArrayList<>();
        for (Parent p : search
        ) {
            list.add(
                    new ParentDTO(
                            p.getId(),
                            p.getName(),
                            p.getEmail(),
                            p.getNic(),
                            p.getAddress(),
                            p.getPhone()
                    )
            );
        }
        return list;
    }


}
