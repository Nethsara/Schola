package me.siyum.schola.controller.students;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.siyum.schola.view.students.tm.AnnouncementsTM;

import java.io.IOException;
import java.sql.SQLException;

public class StudentsNotificationsTableRowController {
    public TextField txtAnnID;
    public TextField txtAnnMessage;
    public JFXButton btnVote;
    public TextField txtClassID;

    public void setData(AnnouncementsTM announcementsTM) throws SQLException, ClassNotFoundException {
        txtClassID.setText(announcementsTM.getFrom());
        txtAnnID.setText(announcementsTM.getId());
        txtAnnMessage.setText(announcementsTM.getMessage());


        btnVote.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/students/StudentVote.fxml"));
                Parent parent = loader.load();
                StudentVoteController controller = loader.getController();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
