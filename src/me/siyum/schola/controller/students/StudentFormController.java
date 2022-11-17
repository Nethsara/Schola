package me.siyum.schola.controller.students;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.ParentBO;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.db.DBConnection;
import me.siyum.schola.dto.ParentDTO;
import me.siyum.schola.dto.StudentDTO;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentFormController {

    public ImageView imgSt;
    public Label lblName;
    public TextField txtName;
    public TextField txtNIC;
    public TextField txtEmail;
    public TextField txtPhone;
    public TextField txtAddress;
    public JFXCheckBox chkAddParent;
    public Label lblStID;
    public Label lblPID;
    public TextField txtPName;
    public TextField txtPNIC;
    public TextField txtPPhone;
    public AnchorPane paneSelectParents;
    public Label lblScholaMark;
    public JFXButton btnCancel;
    public FontAwesomeIconView iconUpload;
    public JFXButton btnSave;
    public JFXComboBox<String> cmbParentID;
    public TextField txtPEmail;
    public JFXDatePicker pickerDOB;
    public JFXComboBox<String> cmbBatch;

    private StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private ParentBO parentBO = BOFactory.getInstance().getBO(BOTypes.PARENT);
    private boolean isSavedParent = true;
    private ParentDTO parentDTO;

    public void initialize() {
        setData();
        txtName.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    lblName.setText(newValue);
                });
    }

    public void uploadStImageOnAction() {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imgSt.setImage(image);
        }
    }

    public void saveStudentOnAction() throws SQLException {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            getData();
            if (!isSavedParent) {
                boolean saveParent = parentBO.saveParent(parentDTO);
                if (saveParent) {
                    boolean save = studentBO.saveStudent(getData());
                    if (save) {
                        connection.commit();
                        new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added the Student").show();
                    } else {
                        connection.setAutoCommit(true);
                        connection.rollback();
                        new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    }
                } else {
                    connection.setAutoCommit(true);
                    connection.rollback();
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }
            } else {
                boolean b = studentBO.saveStudent(getData());
                if (b) new Alert(Alert.AlertType.INFORMATION,
                        "Successfully Added the Student").show();

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
            clear();
            setData();
        }


    }

    private StudentDTO getData() throws SQLException, ClassNotFoundException {
        String stID = lblStID.getText();
        String stName = txtName.getText();
        String stEmail = txtEmail.getText();
        String stNIC = txtNIC.getText();
        String stAddress = txtAddress.getText();
        String stPhone = txtPhone.getText();
        String parentID;
        if (isSavedParent) {
            String tempParentID = cmbParentID.getValue();
            String[] array = tempParentID.split("-");//[D,3]
            parentID = array[0];
        } else {
            parentID = lblPID.getText();
            String parentName = txtPName.getText();
            String parentEmail = txtPEmail.getText();
            String parentPhone = txtPPhone.getText();
            String parentNIC = txtPNIC.getText();

            parentDTO = new ParentDTO(
                    parentID,
                    parentName,
                    parentEmail,
                    parentNIC,
                    stAddress,
                    parentPhone
            );

        }
        int scholaMark = 0;
        if (!btnSave.getText().equalsIgnoreCase("Save")) {
            scholaMark = Integer.parseInt(lblScholaMark.getText());
        }

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

        String batchID = cmbBatch.getValue();

        LocalDate dob = pickerDOB.getValue();

        return new StudentDTO(stID, stName, stEmail, stNIC, blobImage, stAddress, stPhone, parentID, scholaMark, dob, true, false, batchID);
    }

    public void setData(int id) {
        btnSave.setText("Update");
        imgSt.setImage(null);
        try {
            ResultSet rs = CRUDUtil.execute("SELECT image FROM students WHERE id = ?", id);
            if (rs.next()) {
                Blob data = rs.getBlob(1);
                imgSt.setImage(new Image(data.getBinaryStream()));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    private void setStudentID() throws SQLException, ClassNotFoundException {
        String tempStID = studentBO.getLastID();
        if (tempStID.equalsIgnoreCase("")) {
            lblStID.setText("SS-" + 1);
        } else {
            String[] array = tempStID.split("-");
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            lblStID.setText("SS-" + finalizeOrderId);
        }
    }

    private void setParentID() throws SQLException, ClassNotFoundException {
        String tempPID = parentBO.getLastID();
        if (tempPID.equalsIgnoreCase("")) {
            lblPID.setText("SP-" + 1);
        } else {
            String[] array = tempPID.split("-");
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            lblPID.setText("SP-" + finalizeOrderId);
        }


    }

    private void setData() {
        try {
            ArrayList<String> batchList = new ArrayList<>();
            ResultSet res = CRUDUtil.execute("SELECT * FROM batches");
            while (res.next()) {
                batchList.add(res.getString(1));
            }

            ObservableList<String> obBatchList = FXCollections.observableArrayList(batchList);
            cmbBatch.setItems(obBatchList);

            setStudentID();
            setParentID();

            ResultSet set = parentBO.retrieve();
            ArrayList<String> idList = new ArrayList<>();
            while (set.next()) {
                idList.add(set.getString(1));
            }
            ObservableList<String> obList = FXCollections.observableArrayList(idList);
            cmbParentID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void changeParentStatus(ActionEvent actionEvent) {
        if (chkAddParent.isSelected()) {
            paneSelectParents.setVisible(false);
            isSavedParent = false;
        } else {
            isSavedParent = true;
            paneSelectParents.setVisible(true);
        }
    }

    private void clear() {
        txtAddress.clear();
        txtEmail.clear();
        txtName.clear();
        txtNIC.clear();
        txtPEmail.clear();
        txtPhone.clear();
        txtPPhone.clear();
        txtPNIC.clear();
        txtPNIC.clear();
        txtPName.clear();

        cmbBatch.setValue(null);
        cmbParentID.setValue(null);

        pickerDOB.setValue(null);
    }
}

