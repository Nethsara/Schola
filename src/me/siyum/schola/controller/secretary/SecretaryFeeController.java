package me.siyum.schola.controller.secretary;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.FeeBO;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dto.EmployeeDTO;
import me.siyum.schola.dto.FeeDTO;
import me.siyum.schola.util.Env;
import me.siyum.schola.view.secretary.tm.SecretaryFeeTM;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

public class SecretaryFeeController {

    public TableView<SecretaryFeeTM> tblPayment;
    public TableColumn<SecretaryFeeTM, String> colID;
    public TableColumn<SecretaryFeeTM, String> colStID;
    public TableColumn<SecretaryFeeTM, String> colAmount;
    public TableColumn<SecretaryFeeTM, String> colName;
    public TableColumn<SecretaryFeeTM, String> colDate;
    public TableColumn<SecretaryFeeTM, String> colAction;
    EmployeeDTO s = (EmployeeDTO) Env.user;
    ObservableList<SecretaryFeeTM> obList = FXCollections.observableArrayList();

    StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStID.setCellValueFactory(new PropertyValueFactory<>("stID"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
        setData();
    }

    private void setData() {
        FeeBO feeBO = BOFactory.getInstance().getBO(BOTypes.FEE);
        try {
            ArrayList<FeeDTO> feesByID = feeBO.getFeesByID("");

            for (FeeDTO f : feesByID
            ) {
                JFXButton btn = new JFXButton("View Receipt");
                btn.setStyle("-fx-background-color: #111214; -fx-text-fill: #ffffff");
                obList.add(
                        new SecretaryFeeTM(
                                f.getId(),
                                f.getStID(),
                                studentBO.retrieveStudent(f.getStID()).getName(),
                                f.getAmount(),
                                f.getDate(),
                                btn
                        )
                );
                btn.setOnAction(e -> {
                    try {
                        InputStream inputStream = f.getBill().getBinaryStream();
                        String nameSaved = Env.bills + f.getId() + "-Student-" + f.getStID() + ".png";
                        try {
                            Files.copy(inputStream, Paths.get(nameSaved));
                        } catch (FileAlreadyExistsException alreadyHave) {
                            new Alert(Alert.AlertType.WARNING, "File Already Downloaded, Opening now...").show();
                        } finally {
                            File file = new File(nameSaved);
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(file);
                        }

                    } catch (SQLException | IOException ex) {
                        ex.printStackTrace();
                    }
                });
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblPayment.setItems(obList);

    }
}
