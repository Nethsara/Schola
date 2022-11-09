package me.siyum.schola.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import me.siyum.schola.dao.CRUDUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddStudentFromController {
    public ImageView imgSt;

    public void initialize() throws SQLException, ClassNotFoundException, IOException {
        ResultSet execute = CRUDUtil.execute("SELECT image FROM students WHERE sid=1");
        if (execute.next()) {
            InputStream is = execute.getBinaryStream("image");
            OutputStream os = new FileOutputStream("photo.jpg");
            byte[] content = new byte[1024];

            int size = 0;

            while ((size = is.read(content)) != -1) {
                os.write(content, 0, size);
            }

            os.close();
            is.close();

            Image image = new Image("file:photo.jpg", 100, 500, true, true);
            imgSt.setImage(image);
        }
    }

    public void uploadStImageOnAction(MouseEvent mouseEvent) throws IOException, SQLException, ClassNotFoundException {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter(
                ".IMAGE", "jpg", "gif", "png");
        jFileChooser.addChoosableFileFilter(fileNameExtensionFilter);
        int result = jFileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jFileChooser.getSelectedFile();
            FileInputStream fis = new FileInputStream(selectedFile);
            Image img = new Image(selectedFile.toURI().toURL().toExternalForm());
            imgSt.setImage(img);

        }
    }
}
