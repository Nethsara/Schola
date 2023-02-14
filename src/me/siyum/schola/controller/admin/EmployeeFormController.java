package me.siyum.schola.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.dto.EmployeeDTO;
import me.siyum.schola.view.admin.tm.AdminEmployeesTM;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeeFormController {

    private final EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    public ImageView imgSt;
    public Label lblEmployee;

    public TextField txtEmail;
    public TextField txtPhone;
    public TextField txtAddress;
    public TextField txtSalary;
    public JFXComboBox<String> cmbEmpType;
    public Label lblScholaRank;
    public JFXButton btnCancel;
    public JFXButton btnSave;
    public FontAwesomeIconView iconUpload;
    public JFXDatePicker pickerDOB;
    public TextField txtName;
    public Label lblEmpID;
    public AnchorPane paneRank;

    public void initialize() {
        String[] empTypes = {"lecturer", "secretary", "minor", "receptionist"};
        ObservableList<String> empTdypes = FXCollections.observableArrayList(empTypes);
        cmbEmpType.setItems(empTdypes);

        setData();
    }

    private void setData() {
        try {
            setEmpID();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private EmployeeDTO getData() {
        String empID = lblEmpID.getText();
        String empName = txtName.getText();
        String empEmail = txtEmail.getText();
        String empAddress = txtAddress.getText();

        Blob blobImage = null;

        Image image = imgSt.getImage();
        if (image != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            BufferedImage bi = SwingFXUtils.fromFXImage(image, null);
            try {
                ImageIO.write(bi, "jpg", bos);
                blobImage = new SerialBlob(bos.toByteArray());
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }

        String empType = cmbEmpType.getValue();

        double empSalary = Double.parseDouble(txtSalary.getText());
        LocalDate dob = pickerDOB.getValue();

        return new EmployeeDTO(
                empID,
                blobImage,
                empName,
                empAddress,
                empEmail,
                empSalary,
                "SPMM-1",
                empType,
                true
        );
    }

    public void setData(AdminEmployeesTM adminEmployeeTM) throws SQLException, ClassNotFoundException {
        btnSave.setText("Update");
        txtName.setText(adminEmployeeTM.getName());
        txtEmail.setText(adminEmployeeTM.getEmail());
        txtAddress.setText(adminEmployeeTM.getAddress());
        lblEmpID.setText(adminEmployeeTM.getId());
        txtSalary.setText(String.valueOf(adminEmployeeTM.getSalary()));
        cmbEmpType.setValue(adminEmployeeTM.getRole());

        if (adminEmployeeTM.getRole().equalsIgnoreCase("lecturer")) {
            paneRank.setVisible(true);
        }
    }

    public void uploadStImageOnAction() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imgSt.setImage(image);
        }
    }

    public void saveEmployee() throws IOException, SQLException, ClassNotFoundException {
        if (btnSave.getText().equalsIgnoreCase("Save")) {
            try {
                boolean saved = employeeBO.saveEmployee(
                        getData()
                );

                if (saved) {
                    new Alert(Alert.AlertType.INFORMATION, "Saved Employee").show();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/admin/AdminEmployeeManager.fxml"));
                    loader.load();
                    AdminLectureresPageController controller = loader.getController();
                    controller.setData();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Errors saving").show();
                }
            } catch (SQLException | ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        } else {
            boolean saved = employeeBO.updateEmployee(
                    getData()
            );
            if (saved) {
                new Alert(Alert.AlertType.INFORMATION, "Saved Employee").show();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/admin/AdminEmployeeManager.fxml"));
                loader.load();
                AdminLectureresPageController controller = loader.getController();
                controller.setData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Errors saving").show();
            }
        }
    }

    public void changeEmployeeType() {
        lblEmployee.setText(cmbEmpType.getValue());
    }

    private void setEmpID() throws SQLException, ClassNotFoundException {
        String tempID = employeeBO.getLastID();
        if (tempID.equalsIgnoreCase("")) {
            lblEmpID.setText("SEM-" + 1);
        } else {
            String[] array = tempID.split("-");
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            lblEmpID.setText("SEM-" + finalizeOrderId);
        }
    }
}
