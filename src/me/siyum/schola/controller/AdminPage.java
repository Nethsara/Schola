package me.siyum.schola.controller;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class AdminPage {
    public Circle circleImg;
    public AnchorPane mainPane;

    public void initialize(){
        Image im = new javafx.scene.image.Image("me/siyum/schola/assets/images/admin.png", false);
        circleImg.setFill(new ImagePattern(im));
    }
}
