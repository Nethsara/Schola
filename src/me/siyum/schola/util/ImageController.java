package me.siyum.schola.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Blob;
import java.sql.SQLException;

public class ImageController {
    public static ImageView blobToImage(Blob blob) throws SQLException {
        return  new ImageView(new Image(blob.getBinaryStream()));
    }
}
