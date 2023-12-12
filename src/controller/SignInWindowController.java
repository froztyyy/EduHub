/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author lugtu
 */
public class SignInWindowController implements Initializable {

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private TextField si_password;
    private double x = 0;
    private double y = 0;
    @FXML
    private ImageView bgGradient;
    @FXML
    private Pane adminLogin;
    @FXML
    private TextField si_userID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Create a PathTransition for the ImageView along the circular path
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(2.5), bgGradient);
        scaleTransition.setByX(0.2); // Scale factor in the x-direction
        scaleTransition.setByY(0.2); // Scale factor in the y-direction
        scaleTransition.setAutoReverse(true); // Auto reverse the animation

        // Set the cycle count to indefinite for the continuous animation
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);

        // Play the animation
        scaleTransition.play();

    }

//    @FXML
//    private void closeButton(ActionEvent event) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirm Close");
//        alert.setHeaderText("Do you wish to exit the program");
//
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        if (alert.showAndWait().get() == ButtonType.OK) {
//            System.out.println("You successfully logged out");
//            stage.close();
//        }
//    }
    @FXML
    private void closeButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Close");
        alert.setHeaderText("Do you wish to exit the program?");

        // Load custom icon
        Image icon = new Image("/media/icons/custom/Hollow/Unknown.png");

        // Set custom icon size
        double iconSize = 35.0; // Change this value to the desired size
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(iconSize);
        imageView.setFitHeight(iconSize);

        // Set custom icon as the graphic for the alert
        alert.setGraphic(imageView);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            System.out.println("You successfully logged out");

            Platform.exit();
        }
    }

    @FXML
    private void minimizeButton(ActionEvent event) {
        Stage stage = (Stage) adminLogin.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void getBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/signInWindow.fxml"));
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
    private void signInButton(ActionEvent event) {

        connect = database.getConnection();

        try {
            prepare = connect.prepareStatement("SELECT * FROM account_student WHERE StudentID = ? and Password = ?");
            prepare.setString(1, si_userID.getText());
            prepare.setString(2, si_password.getText());
            result = prepare.executeQuery();

            Alert alert;

            if (si_userID.getText().isEmpty() || si_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {
                if (result.next()) {

                    int roleID = result.getInt("RoleID");

                    if (roleID == 3) { // Student
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Welcome!");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Login as Student!");
                        alert.showAndWait();

                        String surname = result.getString("Surname");
                        String studentID = result.getString("studentID");
                        String courseID = result.getString("courseID");
                        String sectionID = result.getString("sectionID");
                        System.out.println("Retrieved: " + studentID + courseID + sectionID);

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/userDashboard.fxml"));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = loader.load();

                        // Get the controller of the loaded FXML file
                        UserDashboardController userDashboardController = loader.getController();

                        // Set the username using the method in UserDashboardController
                        userDashboardController.user_Surname(surname);

                        // Set the studentID using the method in UserDashboardController
                        userDashboardController.user_StudentID(studentID);

                        userDashboardController.user_CourseID(courseID);
                        userDashboardController.user_SectionID(sectionID);

                        ((Node) (event.getSource())).getScene().getWindow().hide();
                        stage.setWidth(1332);
                        stage.setHeight(835);

                        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                        double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
                        double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
                        stage.setX(centerX - 666);
                        stage.setY(centerY - 417.5);

                        Scene scene = new Scene(root, 1332, 835);

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

                    } else if (roleID == 2) { // Officer
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Welcome!");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Login as Officer!");
                        alert.showAndWait();

                        String Surname = result.getString("Surname");
                        String studentID = result.getString("studentID");
                        String courseID = result.getString("courseID");
                        String sectionID = result.getString("sectionID");
                        System.out.println("Retrieved: " + Surname + studentID + courseID + sectionID);

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/officerDashboard.fxml"));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = loader.load();

                        // Get the controller of the loaded FXML file
                        OfficerDashboardController officerDashboardController = loader.getController();

                        // Set the username using the method in UserDashboardController
                        officerDashboardController.user_Surname(Surname);

                        // Set the studentID using the method in UserDashboardController
                        officerDashboardController.user_StudentID(studentID);

                        officerDashboardController.user_CourseID(courseID);
                        officerDashboardController.user_SectionID(sectionID);

                        ((Node) (event.getSource())).getScene().getWindow().hide();
                        stage.setWidth(1332);
                        stage.setHeight(835);

                        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                        double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
                        double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
                        stage.setX(centerX - 666);
                        stage.setY(centerY - 417.5);

                        Scene scene = new Scene(root, 1332, 835);

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

                    } else if (roleID == 1) { // Admin
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Welcome!");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Login as Admin!");
                        alert.showAndWait();

                        String studentID = result.getString("studentID");
                        String password = result.getString("Password");
                        String courseID = result.getString("CourseID");
                        String sectionID = result.getString("SectionID");
                        String firstname = result.getString("Firstname");
                        String middlename = result.getString("Middlename");
                        String surname = result.getString("Surname");

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adminDashboard.fxml"));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = loader.load();

                        // Get the controller of the loaded FXML file
                        AdminDashboardController adminDashboardController = loader.getController();
                        
                        adminDashboardController.user_StudentID(result.getString("studentID"));
                        adminDashboardController.user_Password(result.getString("Password"));
                        adminDashboardController.user_CourseID(result.getString("CourseID"));
                        adminDashboardController.user_SectionID(result.getString("SectionID"));
                        adminDashboardController.user_RoleID(result.getInt("RoleID"));
                        adminDashboardController.user_Firstname(result.getString("Firstname"));
                        adminDashboardController.user_Middlename(result.getString("Middlename"));
                        adminDashboardController.user_Surname(result.getString("Surname"));
                        adminDashboardController.user_Suffix(result.getString("Suffix"));

                        ((Node) (event.getSource())).getScene().getWindow().hide();
                        stage.setWidth(1332);
                        stage.setHeight(835);

                        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                        double centerX = screenBounds.getMinX() + screenBounds.getWidth() / 2.0;
                        double centerY = screenBounds.getMinY() + screenBounds.getHeight() / 2.0;
                        stage.setX(centerX - 666);
                        stage.setY(centerY - 417.5);

                        Scene scene = new Scene(root, 1332, 835);

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
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Wrong Username/Password");
                        alert.showAndWait();
                    }

                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
