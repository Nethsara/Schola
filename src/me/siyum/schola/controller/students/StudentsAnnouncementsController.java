package me.siyum.schola.controller.students;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.AnnouncementsBO;
import me.siyum.schola.bo.custom.NotificationStudentBO;
import me.siyum.schola.dto.AnnouncementsDTO;
import me.siyum.schola.view.students.tm.AnnouncementsTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentsAnnouncementsController {

    private final AnnouncementsBO stBo = (AnnouncementsBO) BOFactory.getInstance().getBO(BOTypes.ANNOUNCEMENTS);
    public JFXListView listAnnouncements;
    NotificationStudentBO noti = (NotificationStudentBO) BOFactory.getInstance().getBO(BOTypes.NOTIFICATION_STUDENT);
    private final ObservableList<AnnouncementsTM> tmList = FXCollections.observableArrayList();

    public void initialize() {
        setData();
    }

    private void setData() {
        ArrayList<AnnouncementsDTO> search = null;

        try {
            search = stBo.search("");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert search != null;
        for (AnnouncementsDTO dto : search
        ) {
            tmList.add(
                    new AnnouncementsTM(
                            dto.getId(),
                            dto.getMessage(),
                            dto.getFrom(),
                            dto.getTo()
                    )
            );
        }
        for (AnnouncementsTM tm : tmList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/me/siyum/schola/view/students/StudentsAnnouncementsTableRow.fxml"));
            HBox anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
                StudentsAnnouncementsTableRowController itemController = fxmlLoader.getController();
                itemController.setData(tm);
                listAnnouncements.getItems().add(anchorPane);
            } catch (IOException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
