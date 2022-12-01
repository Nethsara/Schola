package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.FeeBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.FeeDAO;
import me.siyum.schola.dto.FeeDTO;
import me.siyum.schola.entity.Fee;

import java.sql.SQLException;
import java.util.ArrayList;

public class FeeBOImpl implements FeeBO {
    FeeDAO feeDAO = DAOFactory.getInstance().getDAO(DAOTypes.FEE);

    public ArrayList<FeeDTO> getFeesByID(String stID) throws SQLException, ClassNotFoundException {
        ArrayList<Fee> search = feeDAO.search(stID);
        ArrayList<FeeDTO> list = new ArrayList<>();

        for (Fee f : search
        ) {
            list.add(
                    new FeeDTO(
                            f.getId(),
                            f.getStID(),
                            f.getAmount(),
                            f.getDate(),
                            f.getBill()
                    )
            );
        }
        return list;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return feeDAO.getLastID();
    }
}
