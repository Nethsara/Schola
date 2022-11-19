package me.siyum.schola.controller.admin;

import com.jfoenix.controls.JFXTextArea;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.AnnouncementsBO;
import me.siyum.schola.dto.AnnouncementsDTO;

import java.sql.SQLException;

public class AdminAnnouncement {
    private final AnnouncementsBO stBo = BOFactory.getInstance().getBO(BOTypes.ANNOUNCEMENTS);
    public JFXTextArea messageBox;
    private String announceID;

    public void sendMessage() {
        generateID();
        try {
            System.out.println(announceID);
            stBo.saveAnnouncements(new AnnouncementsDTO(
                    announceID,
                    messageBox.getText(),
                    "admin",
                    "students"
            ));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void generateID() {
        try {
            announceID = stBo.getLastID();
            String[] array = announceID.split("-");//[D,3]
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            announceID = "SA-" + finalizeOrderId;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
