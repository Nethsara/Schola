package me.siyum.schola;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load((getClass().getResource("view/receptionist/ReceptionistStudents.fxml")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.centerOnScreen();
        primaryStage.getIcons().add(new Image("./me/siyum/schola/assets/images/icon-blue.png"));
    }
}
