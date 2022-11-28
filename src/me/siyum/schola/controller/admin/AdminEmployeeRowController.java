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
import me.siyum.schola.view.admin.tm.AdminEmployeesTM;

import java.io.IOException;
import java.sql.SQLException;

public class AdminEmployeeRowController {
    public Button btnAction;
    public TextField txtID;
    public TextField txtStName;
    public TextField txtEmail;
    public TextField txtNIC;
    public TextField txtStatus;
    public Circle circleImage;
    public TextField txtEmType;

    private AdminEmployeesTM adminStudentsTM;

    public void setData(AdminEmployeesTM employerTM) throws SQLException {
        adminStudentsTM = employerTM;
        txtID.setText(employerTM.getId());
        if (!(employerTM.getImage() == null)) {
            Image tm = new Image(employerTM.getImage().getBinaryStream());
            circleImage.setFill(new ImagePattern(tm));
        }
        txtEmail.setText(employerTM.getEmail());
        txtStName.setText(employerTM.getName());
        txtEmType.setText(employerTM.getRole());
        btnAction.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(".././../view/admin/EmployeeForm.fxml"));
                Parent parent = loader.load();
                EmployeeFormController controller = loader.getController();
                controller.setData(adminStudentsTM);
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException | ClassNotFoundException err) {
                err.printStackTrace();
            }
        });
    }

}
