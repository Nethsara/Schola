package me.siyum.schola.controller.admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import me.siyum.schola.controller.students.StudentFormController;
import me.siyum.schola.view.admin.tm.AdminStudentsTM;

import java.io.IOException;
import java.sql.SQLException;

public class AdminStudentsRowController {
    public Button btnAction;
    public TextField txtID;
    public TextField txtStName;
    public TextField txtEmail;
    public TextField txtNIC;
    public TextField txtScholaMarks;
    public TextField txtStatus;
    public Circle circleImage;

    public void setData(AdminStudentsTM studentTM) throws SQLException {
        txtID.setText(studentTM.getId());
        Image tm = new Image(studentTM.getImage().getBinaryStream());
        circleImage.setFill(new ImagePattern(tm));
        txtEmail.setText(studentTM.getEmail());
        txtStName.setText(studentTM.getName());
        txtNIC.setText(studentTM.getNic());
        txtScholaMarks.setText(String.valueOf(studentTM.getScholaMarks()));
        txtStatus.setText(String.valueOf(studentTM.getStatus()));
        btnAction.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(".././../view/students/StudentForm.fxml"));
                Parent parent = loader.load();
                StudentFormController controller = loader.getController();
                controller.setData(studentTM.getId());
                controller.makeUpdateForm();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public void generateReport() {
    }
}
