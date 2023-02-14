package me.siyum.schola.controller.receptionist;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.view.receptionist.tm.ReceptionistStudentTM;

import java.sql.SQLException;
import java.util.Optional;

public class ReceptionistTableRowController {

    public Button btnAction;
    public TextField txtID;
    public TextField txtStName;
    public TextField txtEmail;
    public TextField txtNIC;
    public TextField txtScholaMarks;
    public TextField txtStatus;
    public Circle circleImg;

    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOTypes.STUDENT);

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
        btnAction.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "are you sure whether do you want to delete this student?",
                    ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get() == ButtonType.YES) {
                try {
                    boolean b = studentBO.deleteStudent(txtID.getText());
                    if (b) {
                        new Alert(Alert.AlertType.ERROR, "Successfully deleted!").show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
