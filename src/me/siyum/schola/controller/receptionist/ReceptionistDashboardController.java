package me.siyum.schola.controller.receptionist;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.AttendanceBO;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.bo.custom.TasksBO;
import me.siyum.schola.dto.AttendanceDTO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.dto.TasksDTO;
import me.siyum.schola.dto.ToDoDTO;
import me.siyum.schola.entity.Tasks;
import me.siyum.schola.view.receptionist.tm.ReceptionistTasksTM;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class ReceptionistDashboardController {
    private static final ArrayList<ToDoDTO> toDoList = new ArrayList<>();
    private final TasksBO tasksBO = BOFactory.getInstance().getBO(BOTypes.TASKS);
    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    public JFXButton btnAddToDo;
    public JFXListView<String> listName;
    public TextField txtToDoListItem;
    public JFXButton btnRemove;
    public TableView<ReceptionistTasksTM> tblRefreshments;
    public TableColumn<ReceptionistTasksTM, String> colID;
    public TableColumn<ReceptionistTasksTM, String> colTime;
    public TableColumn<ReceptionistTasksTM, String> colStatus;
    public TableColumn<ReceptionistTasksTM, String> colActions;
    public Label lblPendingSt;
    public Label lblServedRef;
    public Label lblTotalRef;
    public Label lblPendingTasks;
    public Label lblToMarkAttendance;
    public Label lblTime;
    AttendanceBO attendanceBO = BOFactory.getInstance().getBO(BOTypes.ATTENDANCE);

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colActions.setCellValueFactory(new PropertyValueFactory<>("cancel"));
        setData();

        listName.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    btnRemove.setDisable(false);
                });

    }

    public void setData() {
        setLables();
        setTime();
        btnRemove.setDisable(true);
        try {
            ObservableList<ReceptionistTasksTM> tmList = FXCollections.observableArrayList();

            ArrayList<TasksDTO> tasksDTOS = tasksBO.searchTasks("");
            for (TasksDTO t : tasksDTOS) {
                Button btn = new Button("Cancel");

                if (t.getStatus()) {
                    btn.setStyle("-fx-base: #c0392b; -fx-border-radius: 5; -fx-background-radius: 5;");
                } else {
                    btn.setStyle("-fx-base: #2980b9; -fx-border-radius: 5; -fx-background-radius: 5;");
                    btn.setDisable(true);
                }
                ReceptionistTasksTM tm = new ReceptionistTasksTM(
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

    private void setTime() {
        Timeline time = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    lblTime.setText(LocalDateTime.now().format(formatter));
                }), new KeyFrame(Duration.seconds(1)));
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    private void setLables() {
        try {
            setPendingStLbl();
            setRefreshments();
            setToMarkAttendance();
            setServedData();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setServedData() throws SQLException, ClassNotFoundException {
        ArrayList<TasksDTO> tasksDTOS = tasksBO.searchTasks("");
        int total = tasksDTOS.size();

        int notCompleted = 0;

        for (TasksDTO t : tasksDTOS
        ) {
            notCompleted = t.getStatus() ? notCompleted : notCompleted++;
        }

        lblServedRef.setText(String.valueOf(notCompleted));

    }

    private void setToMarkAttendance() throws SQLException, ClassNotFoundException {
        int count = 0;
        ArrayList<AttendanceDTO> allAttendance = attendanceBO.getAllAttendance();

        for (AttendanceDTO a : allAttendance
        ) {
            count = a.isStatus() ? count : count++;
        }
        lblToMarkAttendance.setText(String.valueOf(count));
    }

    private void setRefreshments() throws SQLException, ClassNotFoundException {
        ArrayList<TasksDTO> tasksDTOS = tasksBO.searchTasks("");
        int doneCount = 0;
        int pendingCount = 0;
        for (TasksDTO t : tasksDTOS
        ) {
            if (t.getStatus()) {
                doneCount++;
            } else {
                pendingCount++;
            }
        }

        lblServedRef.setText(String.valueOf(doneCount));
        lblTotalRef.setText(String.valueOf(tasksDTOS.size()));
        lblPendingTasks.setText(String.valueOf(pendingCount));

    }

    private void setPendingStLbl() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> studentDTOS = studentBO.searchStudents(false);
        lblPendingSt.setText(String.valueOf(studentDTOS.size()));
    }

    public void addToList() {
        toDoList.add(new ToDoDTO(
                listName.getItems().size(),
                txtToDoListItem.getText()
        ));
        listName.getItems().add(txtToDoListItem.getText());
        txtToDoListItem.clear();
        btnRemove.setDisable(true);
    }

    public void removeFromList() {
        int selectedID = listName.getSelectionModel().getSelectedIndex();
        listName.getItems().remove(selectedID);
        toDoList.remove(selectedID);
        btnRemove.setDisable(true);

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
                                getLastTaskID(),
                                String.valueOf(LocalDateTime.now()),
                                "Requesting Refreshments",
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

    public String getLastTaskID() throws SQLException, ClassNotFoundException {
        String lastID = tasksBO.getLastID();
        if (lastID.equalsIgnoreCase("")) {
            return "TSK-" + 1;
        } else {
            String[] array = lastID.split("-");//[D,3]
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            return "TSK-" + finalizeOrderId;
        }
    }
}
