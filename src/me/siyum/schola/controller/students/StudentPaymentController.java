package me.siyum.schola.controller.students;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.BatchBO;
import me.siyum.schola.bo.custom.FeeBO;
import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dto.FeeDTO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.util.Env;
import me.siyum.schola.view.students.tm.FeeTM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class StudentPaymentController {
    public TableView<FeeTM> tblPayments;
    public JFXTextField txtPaymentID;
    public JFXTextField txtPayable;
    public JFXTextField txtAmount;
    public TableColumn<FeeTM, String> colDate;
    public TableColumn<FeeTM, String> colID;
    public TableColumn<FeeTM, String> colAmount;
    public TableColumn<FeeTM, String> colActions;
    FileInputStream fis;
    FeeBO feeBO = BOFactory.getInstance().getBO(BOTypes.FEE);
    StudentDTO s = (StudentDTO) Env.user;
    BatchBO batchBO = BOFactory.getInstance().getBO(BOTypes.BATCHES);

    ObservableList<FeeTM> obList = FXCollections.observableArrayList();

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        setData();
    }

    private void setData() {
        tblPayments.getItems().clear();
        try {
            txtPayable.setText(String.valueOf(batchBO.getBatch(s.getBatch()).getFee()));
            setPayID();
            ArrayList<FeeDTO> feesByID = feeBO.getFeesByID(s.getId());
            for (FeeDTO f : feesByID
            ) {
                JFXButton btn = new JFXButton("Download");
                obList.add(
                        new FeeTM(
                                f.getId(),
                                f.getDate(),
                                f.getAmount(),
                                btn
                        )
                );
            }

            tblPayments.setItems(obList);


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setPayID() throws SQLException, ClassNotFoundException {
        String tempStID = feeBO.getLastID();
        if (tempStID.equalsIgnoreCase("")) {
            txtPaymentID.setText("SF-" + 1);
        } else {
            String[] array = tempStID.split("-");
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            txtPaymentID.setText("SF-" + finalizeOrderId);
        }
    }

    public void submitForm() {
        if (Double.parseDouble(txtPayable.getText()) < Double.parseDouble(txtAmount.getText())) {
            new Alert(Alert.AlertType.WARNING, "Please check amount!").show();
        } else {
            try {
                boolean b = CRUDUtil.execute("INSERT INTO fee VALUES(?,?,?,?,?)",
                        txtPaymentID.getText(),
                        s.getId(),
                        Double.parseDouble(txtAmount.getText()),
                        LocalDate.now(),
                        fis
                );

                if (b) {
                    new Alert(Alert.AlertType.INFORMATION, "Success!").show();
                    setData();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadSlip() throws FileNotFoundException {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(null);
        fis = new FileInputStream(file);
    }
}
