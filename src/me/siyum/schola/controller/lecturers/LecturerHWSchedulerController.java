package me.siyum.schola.controller.lecturers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.BatchBO;
import me.siyum.schola.bo.custom.HomeWorkBO;
import me.siyum.schola.dto.BatchDTO;
import me.siyum.schola.dto.HomeWorkDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LecturerHWSchedulerController {
    public TextField txtHWID;
    public TextField txtLecID;
    public JFXComboBox<String> cmbBatchID;
    public JFXDatePicker pickerDate;
    public TextField txtMessage;

    BatchBO batchBO = (BatchBO) BOFactory.getInstance().getBO(BOTypes.BATCHES);
    HomeWorkBO hmw = (HomeWorkBO) BOFactory.getInstance().getBO(BOTypes.HOME_WORK);

    public void initialize() {
        setData();
    }

    private void setData() {
        ObservableList<String> batches = FXCollections.observableArrayList();
        try {
            ArrayList<BatchDTO> btch = batchBO.getBatches("");
            for (BatchDTO b : btch
            ) {
                batches.add(b.getId());
            }
            cmbBatchID.setItems(batches);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void scheduleHomeWork(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean save = hmw.save(
                new HomeWorkDTO(
                        txtHWID.getText(),
                        txtLecID.getText(),
                        LocalDate.now(),
                        pickerDate.getValue(),
                        cmbBatchID.getValue(),
                        txtMessage.getText()
                )
        );

        if (save) {
            new Alert(Alert.AlertType.INFORMATION, "Success").show();
        }
    }
}
