package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.AnnouncementsBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.AnnouncementsDAO;
import me.siyum.schola.dto.AnnouncementsDTO;
import me.siyum.schola.entity.Announcements;

import java.sql.SQLException;
import java.util.ArrayList;

public class AnnouncementBOImpl implements AnnouncementsBO {
    private final AnnouncementsDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.ANNOUNCEMENT);

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return dao.getLastID();
    }

    @Override
    public boolean saveAnnouncements(AnnouncementsDTO announcements) throws SQLException, ClassNotFoundException {

        return dao.save(new Announcements(
                announcements.getId(),
                announcements.getMessage(),
                announcements.getFrom(),
                announcements.getTo()
        ));
    }

    @Override
    public ArrayList<AnnouncementsDTO> search(String s) throws SQLException, ClassNotFoundException {
        ArrayList<AnnouncementsDTO> dtoList = new ArrayList<>();
        ArrayList<Announcements> search = dao.search("");
        for (Announcements announcements : search
        ) {
            dtoList.add(
                    new AnnouncementsDTO(
                            announcements.getId(),
                            announcements.getMessage(),
                            announcements.getFrom(),
                            announcements.getTo()
                    )
            );
        }
        return dtoList;
    }

    @Override
    public boolean updateAnnouncement(AnnouncementsDTO announcementsDTO) throws SQLException, ClassNotFoundException {
        return dao.update(
                new Announcements(
                        announcementsDTO.getId(),
                        announcementsDTO.getMessage(),
                        announcementsDTO.getFrom(),
                        announcementsDTO.getTo()
                )
        );
    }
}
