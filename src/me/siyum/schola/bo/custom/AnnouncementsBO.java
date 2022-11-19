package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.AnnouncementsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AnnouncementsBO {
    String getLastID() throws SQLException, ClassNotFoundException;
    boolean saveAnnouncements(AnnouncementsDTO announcements) throws SQLException, ClassNotFoundException;
    ArrayList<AnnouncementsDTO> search(String s) throws SQLException, ClassNotFoundException;
    boolean updateAnnouncement(AnnouncementsDTO announcementsDTO) throws SQLException, ClassNotFoundException;
}
