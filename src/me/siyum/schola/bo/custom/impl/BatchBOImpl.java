package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.BatchBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.BatchDAO;
import me.siyum.schola.dto.BatchDTO;
import me.siyum.schola.entity.Batch;

import java.sql.SQLException;
import java.util.ArrayList;

public class BatchBOImpl implements BatchBO {

    BatchDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.BATCHES);
    @Override
    public ArrayList<BatchDTO> getBatches(String s) throws SQLException, ClassNotFoundException {
        System.out.println(dao.search(""));
        ArrayList<Batch> search = dao.search(s);
        ArrayList<BatchDTO> batchDTOS = new ArrayList<>();
        for (Batch b: search
             ) {
            batchDTOS.add(new BatchDTO(
                    b.getId(),
                    b.getName()
            ));
        }
        return batchDTOS;
    }
}