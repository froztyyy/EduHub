/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author lugtu
 */
public class FeedBackWindowController implements Initializable {

    @FXML
    private Button btnRateFeedback;
    @FXML
    private Button btnNotNow;
    @FXML
    private AnchorPane feedBackWindow;
    private double x = 0;
    private double y = 0;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void closeButton(ActionEvent event) {
        System.out.println("Closing the feedback window");

        // Get the Stage associated with the feedBackWindow
        Stage stage = (Stage) feedBackWindow.getScene().getWindow();

        // Close the Stage
        stage.close();
    }

    @FXML
    private void notNowButton(ActionEvent event) {
        // Get the Stage associated with the feedBackWindow
        Stage stage = (Stage) feedBackWindow.getScene().getWindow();

        // Close the Stage
        stage.close();
    }

    @FXML
    private void RateFeedbackButton(ActionEvent event) {
        // Open the rate feedback window
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/sendRateCommentWindow.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setWidth(1017);
                stage.setHeight(635);

                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
                double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
                stage.setX(centerX - 508.5);
                stage.setY(centerY - 317.5);

                Scene scene = new Scene(root, 1017, 635);

                stage.setScene(scene);
                stage.show();

                root.setOnMousePressed((mouseEvent) -> {
                    x = mouseEvent.getSceneX();
                    y = mouseEvent.getSceneY();
                });

                root.setOnMouseDragged((mouseEvent) -> {
                    stage.setX(mouseEvent.getScreenX() - x);
                    stage.setY(mouseEvent.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((mouseEvent) -> {
                    stage.setOpacity(1);
                });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
