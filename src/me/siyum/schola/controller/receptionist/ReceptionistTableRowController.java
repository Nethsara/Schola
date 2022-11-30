package me.siyum.schola.controller.receptionist;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import me.siyum.schola.view.receptionist.tm.ReceptionistStudentTM;

import java.sql.SQLException;

public class ReceptionistTableRowController {

    public Button btnAction;
    public TextField txtID;
    public TextField txtStName;
    public TextField txtEmail;
    public TextField txtNIC;
    public TextField txtScholaMarks;
    public TextField txtStatus;
    public Circle circleImg;

    public void setData(ReceptionistStudentTM studentTM) throws SQLException {
        txtID.setText(studentTM.getId());
        Image tm = new Image(studentTM.getImage().getBinaryStream());
        circleImg.setFill(new ImagePattern(tm));
        txtEmail.setText(studentTM.getEmail());
        txtStName.setText(studentTM.getName());
        txtNIC.setText(studentTM.getNic());
        txtScholaMarks.setText(String.valueOf(studentTM.getScholaMarks()));
        txtStatus.setText(String.valueOf(studentTM.getStatus()));

        btnAction = studentTM.getBtn();

    }
}
