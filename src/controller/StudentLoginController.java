/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import controller.CustomAlertController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author jcarl
 */
public class StudentLoginController implements Initializable {

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x = 0;
    private double y = 0;
    @FXML
    private Pane studentLoginWindow;
    @FXML
    private TextField si_username;
    @FXML
    private PasswordField si_password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // WITH ALERT DESIGN
//    private void handleBtnLogin(ActionEvent event) throws ClassNotFoundException {
//        String sql = "SELECT * FROM account WHERE Username = ? and Password = ?";
//        connect = database.getConnection();
//
//        try {
//            prepare = connect.prepareStatement(sql);
//            prepare.setString(1, si_username.getText());
//            prepare.setString(2, si_password.getText());
//            result = prepare.executeQuery();
//
//            if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
//                showAlert(event, "Error Message!", "Please fill all blank fields", "Back");
//            } else {
//                if (result.next()) {
//                    openStudentDashboard(event);
//                } else {
//                    showAlert(event, "Error Message!", "Wrong Username/Password", "Back");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private void showAlert(ActionEvent event, String title, String content, String Button) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomAlert.fxml"));
//            Parent root = loader.load();
//
//            Stage customAlertStage = new Stage();
//            customAlertStage.initStyle(StageStyle.UNDECORATED);
//            customAlertStage.initModality(Modality.APPLICATION_MODAL);
//            customAlertStage.initOwner(((Node) event.getSource()).getScene().getWindow());
//            customAlertStage.setResizable(false);
//
//            Scene scene = new Scene(root);
//            customAlertStage.setScene(scene);
//
//            CustomAlertController controller = loader.getController();
//            controller.setTitle(title);
//            controller.setContent(content);
//            controller.setButton(Button);
//            controller.setStage(customAlertStage);
//
//            customAlertStage.showAndWait();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private void handleBtnCancel(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectRole.fxml"));
//            Parent root = loader.load();
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//
//            stage.setScene(scene);
//            stage.setResizable(true);
//            javafx.geometry.Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//            stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
//            stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
//            stage.show();
//        } catch (IOException e) {
//        }
//    }

    
    
    
    
    @FXML
    private void signInButton(ActionEvent event) {
                String sql = "SELECT * FROM account WHERE username = ? and password = ?";

        connect = database.getConnection();
        

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, si_username.getText());
            prepare.setString(2, si_password.getText());
            result = prepare.executeQuery();

            Alert alert;

            if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

                    Parent root = FXMLLoader.load(getClass().getResource("/view/userDashboard.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    ((Node) (event.getSource())).getScene().getWindow().hide();
                    stage.setWidth(1126);
                    stage.setHeight(654);

                    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                    double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
                    double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
                    stage.setX(centerX - 558.5);
                    stage.setY(centerY - 327);

                    Scene scene = new Scene(root, 1126, 654);

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

                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }

            }

        } catch (Exception e) {

        }
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
        Stage stage = (Stage) studentLoginWindow.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void getBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/selectRoleWindow.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            ((Node) (event.getSource())).getScene().getWindow().hide();
            stage.setWidth(843);
            stage.setHeight(511);

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
            double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
            stage.setX(centerX - 421.5);
            stage.setY(centerY - 255.5);

            Scene scene = new Scene(root, 843, 511);

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
