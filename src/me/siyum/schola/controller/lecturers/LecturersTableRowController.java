package me.siyum.schola.controller.lecturers;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.siyum.schola.view.lecturers.tm.LecturersStudentsTM;

import java.sql.SQLException;

public class LecturersTableRowController {

    public ImageView lblStImg;
    public Button btnAction;
    public TextField txtID;
    public TextField txtStName;
    public TextField txtEmail;
    public TextField txtNIC;
    public TextField txtScholaMarks;
    public TextField txtStatus;

    public void setData(LecturersStudentsTM studentTM) throws SQLException {
        txtID.setText(studentTM.getId());
        lblStImg.setImage(new Image(studentTM.getImage().getBinaryStream()));
        txtEmail.setText(studentTM.getEmail());
        txtStName.setText(studentTM.getName());
        txtNIC.setText(studentTM.getNic());
        txtScholaMarks.setText(String.valueOf(studentTM.getScholaMarks()));
        txtStatus.setText(String.valueOf(studentTM.getStatus()));
btnAction.setText("  View  ");
        btnAction = studentTM.getBtn();

    }
}
