package me.siyum.schola.util;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static Stage stage;

    public static void navigate(Routes routes, Event event) throws IOException {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
        switch (routes) {
            case RECEPTIONIST:
                initUI("headers/ReceptionistPage");
                break;
            default:initUI("WelcomePageForm");
        }
    }

    public static void initUI(String location) throws IOException {
        Scene scene = null;
        scene = new Scene(FXMLLoader.load(Navigation.class.getResource("../view/" + location + ".fxml")));
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
