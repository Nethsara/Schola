package me.siyum.schola.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.TasksBO;
import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dto.TasksDTO;
import me.siyum.schola.entity.Tasks;
import me.siyum.schola.view.receptionist.tm.TasksTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class ReceptionistPageController {
    private final TasksBO tasksBO = BOFactory.getInstance().getBO(BOTypes.TASKS);
    public JFXButton btnAddToDo;
    public JFXListView<String> listName;
    public TextField txtToDoListItem;
    public JFXButton btnRemove;
    public TableView<TasksTM> tblRefreshments;
    public TableColumn colID;
    public TableColumn colTime;
    public TableColumn colStatus;
    public TableColumn colActions;
    public AnchorPane receptionistPane;
    public AnchorPane mainPane;
    public Circle circleImg;

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("cancel"));
        setData();

    }

    public void setData() {
        try {
            ObservableList<TasksTM> tmList = FXCollections.observableArrayList();

            ArrayList<TasksDTO> tasksDTOS = tasksBO.searchTasks("");
            for (TasksDTO t : tasksDTOS) {
                Button btn = new Button("Cancel");

                if (t.getStatus()) {
                    btn.setStyle("-fx-base: #c0392b; -fx-border-radius: 5; -fx-background-radius: 5;");
                } else {
                    btn.setStyle("-fx-base: #2980b9; -fx-border-radius: 5; -fx-background-radius: 5;");
                    btn.setDisable(true);
                }
                TasksTM tm = new TasksTM(
                        t.getId(),
                        t.getTimeStamp(),
                        t.getMessage(),
                        t.getStatus(),
                        btn
                );
                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are you sure you want to cancel?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {
                        try {
                            boolean isDeleted = tasksBO.deleteTasks(tm.getId());
                            if (isDeleted) {
                                setData();
                                new Alert(Alert.AlertType.INFORMATION, "Request Cancelled!").show();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                            }
                        } catch (ClassNotFoundException | SQLException e) {
                            new Alert(Alert.AlertType.ERROR, "DB Loading Error, Please contact system admin");
                        }
                    }
                });
                tmList.add(tm);
            }
            tblRefreshments.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void addToList() {
        try {
            Boolean execute = CRUDUtil.execute("INSERT INTO receptionist_todo VALUES(?,?)",
                    listName.getItems().size(), txtToDoListItem.getText()
            );
            if (execute) {
                new Alert(Alert.AlertType.CONFIRMATION, "Added to \"To do List\" ").show();
                listName.getItems().add(txtToDoListItem.getText());
                txtToDoListItem.clear();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to add \"To do List\" ").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Unknown errors occurred :(").show();
        }

    }

    public void removeFromList() {
        int selectedID = listName.getSelectionModel().getSelectedIndex();
        listName.getItems().remove(selectedID);
        try {
            Boolean execute = CRUDUtil.execute("DELETE FROM receptionist_todo WHERE id=?",
                    selectedID
            );
            if (execute) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted from \"To do List\" ").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete \"To do List\" ").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Unknown errors occurred :(").show();
        }

    }

    public void requestsRefreshments() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "This will send an alert to minor staff, to serve for the guest. " +
                        "Are you sure you want to continue?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            try {
                boolean refreshments = tasksBO.addTasks(new Tasks(
                                tasksBO.getLastID() + 1,
                                String.valueOf(LocalDateTime.now()),
                                "Requesting Refreshements",
                                true
                        )
                );
                if (refreshments) {
                    new Alert(Alert.AlertType.INFORMATION, "Sent alert to minor staff ").show();
                    txtToDoListItem.clear();
                    setData();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Operation Failed ").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Unknown Error").show();
            }
        }
    }

    public void studentsPageLoad(ActionEvent actionEvent) throws IOException {

    }
}
