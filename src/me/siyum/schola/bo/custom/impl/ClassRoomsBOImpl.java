package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.ClassRoomsBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.ClassRoomDAO;
import me.siyum.schola.dto.ClassRoomsDTO;
import me.siyum.schola.entity.ClassRooms;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClassRoomsBOImpl implements ClassRoomsBO {

    ClassRoomDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.CLASS_ROOMS);

    @Override
    public ArrayList<ClassRoomsDTO> getAllClassRooms(String s) throws SQLException, ClassNotFoundException {
        ArrayList<ClassRoomsDTO> arList = new ArrayList<>();

        ArrayList<ClassRooms> allClassRooms = dao.search("");
        for (ClassRooms c : allClassRooms
        ) {


            arList.add(
                    new ClassRoomsDTO(
                            c.getId(),
                            c.getSpace()
                    )
            );
        }

        return arList;
    }
}
