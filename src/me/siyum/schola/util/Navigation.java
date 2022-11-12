package me.siyum.schola.util;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    private static Stage stage;
    private static AnchorPane pane;

    public static void navigate(Routes routes, Event event, String token) throws IOException {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
        switch (routes) {
            case RECEPTIONIST:
                initUI("headers/ReceptionistPage");
                stage.setTitle(token);
                break;
            default:
                initUI("WelcomePageForm");
        }
    }

    public static void setFrame(Routes routes, AnchorPane pane) throws IOException {
        switch (routes) {
            case RECEPTIONIST_STUDENTS:
                setPanel("receptionist/Students");
                break;
        }
        stage = (Stage) pane.getScene().getWindow();
    }

    public static void setPanel(String location) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(Navigation.class.getResource("../view/" + location + ".fxml")))));
    }

    public static void initUI(String location) throws IOException {
        Scene scene = null;
        scene = new Scene(FXMLLoader.load(Navigation.class.getResource("../view/" + location + ".fxml")));
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
