package me.siyum.schola.controller.receptionist;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.siyum.schola.view.receptionist.tm.ReceptionistStudentTM;

import java.sql.SQLException;

public class TableRowContoller {

    public ImageView lblStImg;
    public JFXButton btnAction;
    public TextField txtID;
    public TextField txtStName;
    public TextField txtEmail;
    public TextField txtNIC;
    public TextField txtScholaMarks;
    public TextField txtStatus;

    public void setData(ReceptionistStudentTM studentTM) throws SQLException {
        txtID.setText(studentTM.getId());
        lblStImg.setImage(new Image(studentTM.getImage().getBinaryStream()));
        txtEmail.setText(studentTM.getEmail());
        txtStName.setText(studentTM.getName());
        txtNIC.setText(studentTM.getNic());
        txtScholaMarks.setText(String.valueOf(studentTM.getScholaMarks()));
        txtStatus.setText(String.valueOf(studentTM.getStatus()));

    }
}
