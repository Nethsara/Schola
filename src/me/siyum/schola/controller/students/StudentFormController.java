package me.siyum.schola.controller.students;

import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.*;
import me.siyum.schola.dto.BatchDTO;
import me.siyum.schola.dto.ParentDTO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.dto.UsersDTO;
import me.siyum.schola.util.Mailing;
import me.siyum.schola.util.Validator;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class StudentFormController {

    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOTypes.STUDENT);

    private final SaveStudentBO saveStudentBO = (SaveStudentBO) BOFactory.getInstance().getBO(BOTypes.SAVE_STUDENTS);
    private final ParentBO parentBO = (ParentBO) BOFactory.getInstance().getBO(BOTypes.PARENT);
    private final BatchBO batchBO = (BatchBO) BOFactory.getInstance().getBO(BOTypes.BATCHES);
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
    public JFXTextField txtParentName;
    public JFXButton btnReject;
    UsersBO usersBO = (UsersBO) BOFactory.getInstance().getBO(BOTypes.USERS);
    private boolean isSavedParent = true;
    private ParentDTO parentDTO;

    private static String generatePassword() {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public void initialize() {
        setData();
        txtName.textProperty()
                .addListener((observable, oldValue, newValue) -> lblName.setText(newValue));

        txtEmail.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (Validator.isEmailMatch(txtEmail.getText())) {
                        txtEmail.setStyle("-jfx-unfocus-color : black");
                        txtEmail.setStyle("-jfx-focus-color : #4059a9");
                    } else {
                        txtEmail.setStyle("-jfx-unfocus-color : red");
                        txtEmail.setStyle("-jfx-focus-color : red");
                    }
                });

        txtPEmail.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (Validator.isEmailMatch(txtEmail.getText())) {
                        txtEmail.setStyle("-jfx-unfocus-color : black");
                        txtEmail.setStyle("-jfx-focus-color : #4059a9");
                    } else {
                        txtEmail.setStyle("-jfx-unfocus-color : red");
                        txtEmail.setStyle("-jfx-focus-color : red");
                    }
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
        if (btnSave.getText().equalsIgnoreCase("Save")) {
            try {
                StudentDTO sdTO = getData();
                if (!(sdTO == null)) {
                    sendToStudentBO(parentDTO, sdTO);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (btnSave.getText().equalsIgnoreCase("Accept")) {
            acceptStudents();
        } else if (btnSave.getText().equalsIgnoreCase("Update")) {
            updateStudents();
        }


    }

    private void updateStudents() {
        try {
            StudentDTO sdTO = getData();
            if (!(sdTO == null)) {
                sdTO.setApproval(true);
                sdTO.setStatus(true);
                boolean b = studentBO.updateStudent(sdTO);
                if (b) new Alert(Alert.AlertType.INFORMATION,
                        "Successfully Updated the Student").show();
                clear();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private UsersDTO generateUser() {
        String id = "";
        try {
            String tempStID = usersBO.getLastID();
            if (tempStID.equalsIgnoreCase("")) {
                id = "UNS-" + 1;
            } else {
                String[] array = tempStID.split("-");
                int tempNumber = Integer.parseInt(array[1]);
                int finalizeOrderId = tempNumber + 1;
                id = "UNS-" + finalizeOrderId;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new UsersDTO(
                id,
                txtName.getText().split(" ")[0].toLowerCase(Locale.ROOT),
                generatePassword(),
                "student",
                lblStID.getText()
        );

    }

    private void sendToStudentBO(ParentDTO pdTO, StudentDTO sdTO) throws SQLException, ClassNotFoundException {
        UsersDTO usersDTO = generateUser();

        if (!isSavedParent) {
            boolean b = saveStudentBO.saveStudentWithNewParent(pdTO, sdTO, usersDTO);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Operation Success").show();
                clear();
                setData();
            }
        } else {
            boolean b = saveStudentBO.saveStudentWithOldParent(pdTO, sdTO, usersDTO);
            if (b) {
                Mailing.startThread(txtEmail.getText(), "Welcome to Schola LMS", "Here are your credentials to login to Schola LMS." +
                        "\n UserName : " + usersDTO.getUserName() + "\n Password : " + usersDTO.getPassword() + "\n Thanks for Choosing Schola LMS");
                new Alert(Alert.AlertType.CONFIRMATION, "Operation Success").show();
                clear();
                setData();
            }
        }
    }

    private StudentDTO getData() throws SQLException, ClassNotFoundException {
        if (txtName.getText().isEmpty() && txtEmail.getText().isEmpty() && txtNIC.getText().isEmpty()
                && txtAddress.getText().isEmpty() && txtPhone.getText().isEmpty() && txtPName.getText().isEmpty() && txtParentName.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill the all data").showAndWait();
        }
        String stID = lblStID.getText();
        String stName = txtName.getText();
        String stEmail = txtEmail.getText();
        String stNIC = txtNIC.getText();
        String stAddress = txtAddress.getText();
        String stPhone = txtPhone.getText();
        String parentID;
        if (isSavedParent) {
            parentID = cmbParentID.getValue();
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

        if (Validator.isNICMatch(stNIC)) {
            if (Validator.isEmailMatch(stEmail)) {
                if (Validator.isNumberMatch(stPhone)) {
                    return new StudentDTO(stID, stName, stEmail, stNIC, blobImage, stAddress, stPhone, parentID, scholaMark,
                            dob, true, false, batchID, "male", LocalDate.now());
                } else {
                    new Alert(Alert.AlertType.ERROR, "Check your phone number").show();
                    return null;
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Check email").show();
                return null;
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Check your NIC").show();
            return null;
        }
    }

    public void setData(String id) {
        btnSave.setText("Update");
        imgSt.setImage(null);
        try {
            StudentDTO st = studentBO.retrieveStudent(id);
            lblStID.setText(st.getId());
            lblName.setText(st.getName());
            txtName.setText(st.getName());
            txtNIC.setText(st.getNic());
            txtEmail.setText(st.getEmail());
            pickerDOB.setValue(st.getDob());
            txtPhone.setText(st.getPhone());
            txtAddress.setText(st.getAddress());
            cmbParentID.setValue(st.getParentID());
            cmbBatch.setValue(st.getBatch());
            lblScholaMark.setText(String.valueOf(st.getScholaMark()));
            Blob data = st.getImage();
            imgSt.setImage(new Image(data.getBinaryStream()));

            ParentDTO parent = parentBO.getParentByID(cmbParentID.getValue());
            txtParentName.setText(parent.getName());

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

    private void setBatchID() throws SQLException, ClassNotFoundException {
        ArrayList<BatchDTO> batches = batchBO.getBatches("");
        ObservableList<String> obBatchList = FXCollections.observableArrayList();

        for (BatchDTO b : batches
        ) {
            obBatchList.add(b.getId());
        }

        cmbBatch.setItems(obBatchList);
    }

    private void setData() {
        try {
            setBatchID();

            if (btnSave.getText().equalsIgnoreCase("Save")) {
                lblScholaMark.setText("0");
            }

            setStudentID();
            setParentID();

            ArrayList<ParentDTO> parents = parentBO.getParents();
            ArrayList<String> idList = new ArrayList<>();
            for (ParentDTO p : parents
            ) {
                idList.add(p.getId());
            }
            ObservableList<String> obList = FXCollections.observableArrayList(idList);
            cmbParentID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void makeApprovalForm() {
        btnSave.setText("Accept");
        btnReject.setVisible(true);
    }

    public void changeParentStatus() {
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

        lblName.setText("Example Name");

        imgSt.setImage(new Image("me/siyum/schola/assets/images/stImg.png"));

        txtAddress.setStyle("-jfx-unfocus-color : black");
        txtName.setStyle("-jfx-unfocus-color : black");
        txtEmail.setStyle("-jfx-unfocus-color : black");
        txtPhone.setStyle("-jfx-unfocus-color : black");
        txtPNIC.setStyle("-jfx-unfocus-color : black");
        txtPName.setStyle("-jfx-unfocus-color : black");

    }

    public void setParentIDOnAction() {
        try {
            ParentDTO parent = parentBO.getParentByID(cmbParentID.getValue());
            txtParentName.setText(parent.getName());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void rejectStudent() {
        try {

            StudentDTO sdTO = getData();
            if (!(sdTO == null)) {
                sdTO.setApproval(false);
                sdTO.setStatus(false);
                boolean b = studentBO.updateStudent(sdTO);
                if (b) new Alert(Alert.AlertType.INFORMATION,
                        "Successfully Rejected the Student").show();
                clear();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void acceptStudents() {
        try {
            StudentDTO sdTO = getData();
            sdTO.setApproval(true);
            sdTO.setStatus(true);
            boolean b = studentBO.updateStudent(sdTO);
            if (b) new Alert(Alert.AlertType.INFORMATION,
                    "Successfully Accepted the Student").show();
            clear();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void makeUpdateForm() {
        btnSave.setText("Update");
        btnReject.setVisible(false);
    }
}

