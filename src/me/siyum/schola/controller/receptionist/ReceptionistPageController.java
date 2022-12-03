package me.siyum.schola.controller.receptionist;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.dto.EmployeeDTO;
import me.siyum.schola.util.Env;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class ReceptionistPageController {

    public AnchorPane receptionistPane;
    public AnchorPane mainPane;
    public Circle circleImgLeft;

    EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);

    public void initialize() throws IOException {
        Navigation.navigate(Routes.RECEPTIONIST_HOME, mainPane);
        setData();
    }

    private void setData() {
        try {
            Env.user = employeeBO.getEmployeeByID(employeeBO.getIDByToken(Env.token, "receptionist"));
            EmployeeDTO s = (EmployeeDTO) Env.user;
            Blob data = s.getImage();
            Image im = new Image(data.getBinaryStream());
            circleImgLeft.setFill(new ImagePattern(im));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void studentsPageLoad(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONIST_STUDENTS, mainPane);
    }

    public void salaryPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONIST_SALARY, mainPane);
    }

    public void receptionistHome(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONIST_HOME, mainPane);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, actionEvent);
    }

    public void attendancePage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.RECEPTIONIST_ATTENDANCE, mainPane);
    }
}
