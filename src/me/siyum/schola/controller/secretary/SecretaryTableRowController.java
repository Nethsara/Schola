package me.siyum.schola.controller.secretary;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import me.siyum.schola.controller.students.StudentFormController;
import me.siyum.schola.view.secretary.tm.SecretartStudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SecretaryTableRowController implements Initializable {
    public TextField txtID;
    public TextField txtStName;
    public TextField txtEmail;
    public TextField txtNIC;
    public TextField txtScholaMarks;
    public TextField txtStatus;
    public Button btnAction;
    public Circle circleImage;

    public void setData(SecretartStudentTM studentTM) throws SQLException {
        txtID.setText(studentTM.getId());
        if (!(studentTM.getImage() == null)) {
            Image tm = new Image(studentTM.getImage().getBinaryStream());
            circleImage.setFill(new ImagePattern(tm));
        }
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
                controller.makeApprovalForm();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.show();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnAction(ActionEvent actionEvent) {
    }
}
