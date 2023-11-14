/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package main;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author jcarl
 */
public class EduHub extends Application {

    private double x = 0;
    private double y = 0;

        @Override
    public void start(Stage stage) throws Exception {

        // Create a splash screen stage
        Stage splashStage = new Stage(StageStyle.TRANSPARENT);
        Parent splashRoot;
        try {
            splashRoot = FXMLLoader.load(getClass().getResource("/view/splashScreen.fxml"));
            Scene splashScene = new Scene(splashRoot, 741, 432); // Set the splash screen size
            splashScene.getStylesheets().add(getClass().getResource("/style/Eduhub.css").toExternalForm()); // Load the CSS

            // Apply the CSS class to the stage
            splashScene.getRoot().getStyleClass().add("rounded-stage");

            splashStage.setScene(splashScene);

            // Calculate X and Y coordinates to center the splash screen
            double screenWidth = Screen.getPrimary().getBounds().getWidth();
            double screenHeight = Screen.getPrimary().getBounds().getHeight();
            double xPos = (screenWidth - 741) / 2; // Adjusted for the splash screen width
            double yPos = (screenHeight - 432) / 2; // Adjusted for the splash screen height
            splashStage.setX(xPos);
            splashStage.setY(yPos);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle FXML loading error here
            return;
        }

        // Show the splash screen
        splashStage.show();

        // Simulate initialization work (replace with your actual initialization code)
        Thread initThread = new Thread(() -> {
            try {
                Thread.sleep(10000); // Simulate 10 seconds of initialization time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Load the main application window
            Platform.runLater(() -> {
                splashStage.close(); // Close the splash screen
                loadMainApp(stage);
            });
        });

        initThread.start();
    }

    private void loadMainApp(Stage primaryStage) {
        try {

            InputStream iconStream = getClass().getResourceAsStream("/media/EduHub_Logo_v2.png");
            if (iconStream != null) {
                Image icon = new Image(iconStream);
                primaryStage.getIcons().add(icon); // Set the icon for the stage 
            }

            primaryStage.initStyle(StageStyle.TRANSPARENT);
            Parent root = FXMLLoader.load(getClass().getResource("/view/getStartedWIndow.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setWidth(600);
            primaryStage.setHeight(400);

            root.setOnMousePressed((event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((event) -> {
                primaryStage.setX(event.getScreenX() - x);
                primaryStage.setY(event.getScreenY() - y);

                primaryStage.setOpacity(.8);
            });

            root.setOnMouseReleased((event) -> {
                primaryStage.setOpacity(1);
            });

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getMinX() + (screenBounds.getWidth() - 600) / 2;
            double centerY = screenBounds.getMinY() + (screenBounds.getHeight() - 400) / 2;
            primaryStage.setX(centerX);
            primaryStage.setY(centerY);

            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
            // Handle loading the main app window error here
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
