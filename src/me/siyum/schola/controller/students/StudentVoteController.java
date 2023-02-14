package me.siyum.schola.controller.students;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.bo.custom.LecturerVoteBO;
import me.siyum.schola.dto.LecturerVoteDTO;

import java.sql.SQLException;
import java.time.LocalDate;

public class StudentVoteController {
    private final EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private final LecturerVoteBO lecturerVoteBO = (LecturerVoteBO) BOFactory.getInstance().getBO(BOTypes.LECTURER_VOTE);
    public JFXRadioButton vote5;
    public JFXRadioButton vote4;
    public JFXRadioButton vote3;
    public JFXRadioButton vote2;
    public JFXRadioButton vote1;
    public Label lblLetName;
    LecturerVoteDTO lecturerVoteDTO;

    public void setData(LecturerVoteDTO lecturerVoteDTO) {
        try {
            this.lecturerVoteDTO = lecturerVoteDTO;
            lblLetName.setText(employeeBO.getEmployeeByID(lecturerVoteDTO.getLecturer()).getName());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void castVote(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int vote = vote5.isSelected() ? 5 :
                vote4.isSelected() ? 4 :
                        vote3.isSelected() ? 3 :
                                vote2.isSelected() ? 2 :
                                        vote1.isSelected() ? 1 : 0;
        if (vote == 0) {
            new Alert(Alert.AlertType.WARNING, "Please vote!").show();
        } else {
            boolean voted = lecturerVoteBO.vote(
                    new LecturerVoteDTO(
                            lecturerVoteDTO.getLecturer(),
                            vote,
                            LocalDate.now()
                    )
            );

            if (voted) {
                new Alert(Alert.AlertType.INFORMATION, "Success").show();
            }
        }
    }
}
