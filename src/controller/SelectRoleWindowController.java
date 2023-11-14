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
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lugtu
 */
public class SelectRoleWindowController implements Initializable {

    @FXML
    private Pane selectRoleWindow;
    
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You are about to logout");
        alert.setContentText("Do you want to save before exiting?");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You successfully logged out");
            stage.close();
        }
    }

    @FXML
    private void minimizeButton(ActionEvent event) {
        Stage stage = (Stage) selectRoleWindow.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void studentRole(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/StudentLogin.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            ((Node) (event.getSource())).getScene().getWindow().hide();
            stage.setWidth(785);
            stage.setHeight(514);

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
            double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
            stage.setX(centerX - 392.5);
            stage.setY(centerY - 257);

            Scene scene = new Scene(root, 785, 514);

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

    @FXML
    private void adminRole(ActionEvent event) {
    }

    @FXML
    private void officerRole(ActionEvent event) {
    }

}
