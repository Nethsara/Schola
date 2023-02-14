package me.siyum.schola.controller.students;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dto.HomeWorkDTO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.util.Env;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;

public class StudentHWUploadController {
    public TextField txtHWID;
    public TextField txtStID;
    FileInputStream fis;
    StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private final StudentDTO s = (StudentDTO) Env.user;

    public void setData(HomeWorkDTO h) {
        txtStID.setText(s.getId());
        txtHWID.setText(h.getId());
    }

    public void uploadImageOnAction() throws FileNotFoundException {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        fis = new FileInputStream(file);
    }

    public void completeNow(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean b = CRUDUtil.execute("INSERT INTO homework_students VALUES(?,?,?,?,?,?)",
                txtHWID.getText(),
                txtStID.getText(),
                studentBO.retrieveStudent(txtStID.getText()).getName(),
                fis,
                LocalDate.now(),
                false
        );
        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Success!").show();
        }
    }
}
