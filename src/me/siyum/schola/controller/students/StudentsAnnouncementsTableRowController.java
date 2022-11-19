package me.siyum.schola.controller.students;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.view.students.tm.AnnouncementsTM;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentsAnnouncementsTableRowController {
    public TextField txtAnnID;
    public TextField txtAnnMessage;
    public TextField txtAnnFrom;
    public JFXButton btnAction;

    public void setData(AnnouncementsTM announcementsTM) throws SQLException, ClassNotFoundException {
        txtAnnFrom.setText(announcementsTM.getFrom());
        txtAnnID.setText(announcementsTM.getId());
        txtAnnMessage.setText(announcementsTM.getMessage());

        setButton(announcementsTM);

        btnAction.setOnAction(e -> {
            try {
                if (btnAction.getText().equalsIgnoreCase("Mark As Read")) {
                    boolean b = CRUDUtil.execute("UPDATE announcements_students SET status=false WHERE anID=?",
                            announcementsTM.getId());
                    if (b) {
                        new Alert(Alert.AlertType.INFORMATION, "Marked as read!").show();
                        setButton(announcementsTM);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Error!").show();
                    }
                } else {
                    boolean b = CRUDUtil.execute("UPDATE announcements_students SET status=true WHERE anID=?",
                            announcementsTM.getId());
                    if (b) {
                        new Alert(Alert.AlertType.INFORMATION, "Marked as read!").show();
                        setButton(announcementsTM);

                    } else {
                        new Alert(Alert.AlertType.ERROR, "Error!").show();
                    }
                }
            } catch (java.sql.SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

        });
    }

    public void setButton(AnnouncementsTM announcementsTM) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT status FROM announcements_students WHERE anID=?",
                announcementsTM.getId());
        if (res.next()) {
            boolean status = res.getBoolean(1);
            if (!status) {
                btnAction.setText("Mark as Unread");
                txtAnnMessage.setOpacity(0.5);
                txtAnnFrom.setOpacity(0.5);
                txtAnnID.setOpacity(0.5);
            } else {
                btnAction.setText("Mark as Read");
                txtAnnMessage.setOpacity(1);
                txtAnnFrom.setOpacity(1);
                txtAnnID.setOpacity(1);
            }
        }
    }
}
