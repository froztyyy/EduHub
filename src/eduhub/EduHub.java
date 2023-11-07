/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package eduhub;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author jcarl
 */
public class EduHub extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        InputStream iconStream = getClass().getResourceAsStream("/media/EduHub_Logo.png");
            if (iconStream != null) {
                Image icon = new Image(iconStream);
                primaryStage.getIcons().add(icon);
            }
        
        Parent root = FXMLLoader.load(getClass().getResource("selectRole.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("EduHub");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        primaryStage.show();;
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
