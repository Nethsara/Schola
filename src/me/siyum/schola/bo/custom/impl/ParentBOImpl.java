package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.ParentBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.impl.ParentDAOImpl;
import me.siyum.schola.dto.ParentDTO;
import me.siyum.schola.entity.Parent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParentBOImpl implements ParentBO {
    private ParentDAOImpl dao = DAOFactory.getInstance().getDAO(DAOTypes.PARENT);

    @Override
    public int getLastID() throws SQLException, ClassNotFoundException {
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
    public ResultSet retrieve(int id) throws SQLException, ClassNotFoundException {
        return dao.retrieve(id);
    }

    @Override
    public ResultSet retrieve() throws SQLException, ClassNotFoundException {
        return dao.retrieve();
    }


}
