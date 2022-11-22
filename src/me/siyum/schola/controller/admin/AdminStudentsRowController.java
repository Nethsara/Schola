package me.siyum.schola.controller.admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import me.siyum.schola.controller.students.StudentFormController;
import me.siyum.schola.view.admin.tm.AdminStudentsTM;

import java.io.IOException;
import java.sql.SQLException;

public class AdminStudentsRowController {
    public ImageView lblStImg;
    public Button btnAction;
    public TextField txtID;
    public TextField txtStName;
    public TextField txtEmail;
    public TextField txtNIC;
    public TextField txtScholaMarks;
    public TextField txtStatus;

    private AdminStudentsTM adminStudentsTM;

    public void setData(AdminStudentsTM studentTM) throws SQLException {
        adminStudentsTM = studentTM;
        txtID.setText(studentTM.getId());
        lblStImg.setImage(new Image(studentTM.getImage().getBinaryStream()));
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
