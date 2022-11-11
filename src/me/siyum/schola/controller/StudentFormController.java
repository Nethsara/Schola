package me.siyum.schola.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
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

    private StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private ParentBO parentBO = BOFactory.getInstance().getBO(BOTypes.PARENT);
    private boolean isSavedParent = true;
    private ParentDTO parentDTO;

    public void initialize() throws ClassNotFoundException {
        setData();
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
                if (b) new Alert(Alert.AlertType.CONFIRMATION,
                        "Successfully Added the Student").show();

            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.CONFIRMATION,
                    "Error occured when adding student").show();
        } finally {
            connection.setAutoCommit(true);
            clear();
            setData();
        }


    }

    private StudentDTO getData() throws SQLException, ClassNotFoundException {
        int stID = Integer.parseInt(lblStID.getText());
        String stName = txtName.getText();
        String stEmail = txtEmail.getText();
        String stNIC = txtNIC.getText();
        String stAddress = txtAddress.getText();
        String stPhone = txtPhone.getText();
        System.out.println(stPhone);
        int parentID;
        if (isSavedParent) {
            String tempOrderId = cmbParentID.getValue();
            String[] array = tempOrderId.split("-");//[D,3]
            parentID = Integer.parseInt(array[0]);
        } else {
            parentID = Integer.parseInt(lblPID.getText());
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

            System.out.println(parentDTO);
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
        return new StudentDTO(stID, stName, stEmail, stNIC, blobImage, stAddress, stPhone, parentID, scholaMark);
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

    private void setData() {
        try {
            lblStID.setText(String.valueOf(studentBO.getLastID() + 1));
            lblPID.setText(String.valueOf(parentBO.getLastID() + 1));
            ResultSet set = parentBO.retrieve();
            ArrayList<String> idList = new ArrayList<>();
            while (set.next()) {
                idList.add(set.getString(1) + "-" + set.getString(2));
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
            System.out.println("isNotSaved");
        } else {
            isSavedParent = true;
            paneSelectParents.setVisible(true);
            System.out.println("Saved");
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
    }
}

