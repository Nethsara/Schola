package me.siyum.schola.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPageFormController {
    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/headers/ReceptionistPage.fxml")));
        stage.setScene(scene);
        stage.setTitle("fx");
    }
}
