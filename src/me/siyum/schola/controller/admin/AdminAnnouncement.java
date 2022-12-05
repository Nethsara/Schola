package me.siyum.schola.controller.admin;

import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.AnnouncementsBO;
import me.siyum.schola.dto.AnnouncementsDTO;
import me.siyum.schola.view.admin.tm.AdminAnnouncmentTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class AdminAnnouncement {
    private final AnnouncementsBO stBo = BOFactory.getInstance().getBO(BOTypes.ANNOUNCEMENTS);
    private final ObservableList<AdminAnnouncmentTM> list = FXCollections.observableArrayList();
    public JFXTextArea messageBox;
    public TableView<AdminAnnouncmentTM> tblAnnouncements;
    public TableColumn<AdminAnnouncmentTM, String> colID;
    public TableColumn<AdminAnnouncmentTM, String> colTo;
    public TableColumn<AdminAnnouncmentTM, String> colMessage;
    public TableColumn<AdminAnnouncmentTM, String> colActions;
    AnnouncementsBO announcementsBO = BOFactory.getInstance().getBO(BOTypes.ANNOUNCEMENTS);

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMessage.setCellValueFactory(new PropertyValueFactory<>("message"));
        colTo.setCellValueFactory(new PropertyValueFactory<>("to"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("btn"));
        setData();
    }

    private void setData() {
        try {
            ArrayList<AnnouncementsDTO> search = announcementsBO.search("");

            for (AnnouncementsDTO ad : search
            ) {
                Button btn = new Button("Delete");
                list.add(
                        new AdminAnnouncmentTM(
                                ad.getId(),
                                ad.getTo(),
                                ad.getMessage(),
                                btn
                        )
                );

                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();

                    if (buttonType.get() == ButtonType.YES) {
                        for (AdminAnnouncmentTM tm : list) {
                            list.remove(tm);
                            //delete code
                            tblAnnouncements.refresh();
                            return;
                        }
                    }

                });
            }
            tblAnnouncements.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        try {
            stBo.saveAnnouncements(new AnnouncementsDTO(
                    generateID(),
                    messageBox.getText(),
                    "admin",
                    "students"
            ));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String generateID() {
        try {
            String lastID = stBo.getLastID();
            String[] array = lastID.split("-");//[D,3]
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            return "SA-" + finalizeOrderId;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "SA-1";
    }
}
